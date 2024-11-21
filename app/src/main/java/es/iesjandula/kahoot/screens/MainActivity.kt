package es.iesjandula.kahoot.screens

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import es.iesjandula.kahoot.R
import es.iesjandula.kahoot.database.PreguntaDb

class MainActivity : AppCompatActivity() {
    private lateinit var database: PreguntaDb

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Inicializacion de la bbdd
        database = PreguntaDb.getDatabase(this)

        // Declaracion e inicializacion del toolbar
        val toolbar = findViewById<Toolbar>(R.id.appBar)
        setSupportActionBar(toolbar)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Declaracion y asignacion del Boton Jugar
        val btnJugar = findViewById<Button>(R.id.btnJugar)

        // Asignacion de la animacion para el texto Kahoot
        val animator = ObjectAnimator.ofFloat(findViewById<TextView>(R.id.tvKahoot), "translationY", +100f, -100f)
        animator.duration = 1200
        animator.repeatCount = ObjectAnimator.INFINITE
        animator.repeatMode = ObjectAnimator.REVERSE
        animator.start()


        // Si se hace click sobre el boton jugar
        btnJugar.setOnClickListener{ startActivity(Intent(this, JugarActivity::class.java))}
        database.preguntaDao().getAllLive().observe(this) { listado ->

            btnJugar.setOnClickListener {
                val numeroDePreguntas = listado.size

                if (numeroDePreguntas < 8) {
                    val mensaje = if (numeroDePreguntas == 1) {
                        "Hay $numeroDePreguntas pregunta, es necesario 8 como mínimo"
                    } else {
                        "Hay $numeroDePreguntas preguntas, es necesario 8 como mínimo"
                    }
                    Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
                } else {
                    val intent = Intent(this, JugarActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.actionconfigurar ->
                {
                startActivity(Intent(this, ConfigurarActivity::class.java))
                true
            }
            R.id.actionconsultar ->
            {
                database.preguntaDao().getAllLive().observe(this) { listado ->
                    if(listado.isEmpty())
                    {
                        Toast.makeText(this, "No hay preguntas creadas", Toast.LENGTH_LONG).show()
                    }
                    else
                    {
                        startActivity(Intent(this, ConsultarActivity::class.java))
                    }
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
