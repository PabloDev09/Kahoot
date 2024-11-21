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

        database = PreguntaDb.getDatabase(this)

        val toolbar = findViewById<Toolbar>(R.id.appBar)
        setSupportActionBar(toolbar)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnJugar = findViewById<Button>(R.id.btnJugar)
        val animator = ObjectAnimator.ofFloat(findViewById<TextView>(R.id.tvKahoot), "translationY", -100f, +100f)
        animator.duration = 1800
        animator.repeatCount = ObjectAnimator.INFINITE
        animator.repeatMode = ObjectAnimator.REVERSE
        animator.start()


        btnJugar.setOnClickListener{
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

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.actionconfigurar -> {
                val intent = Intent(this, ConfigurarActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.actionconsultar ->
            {
                val intent = Intent(this, PreguntasActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
