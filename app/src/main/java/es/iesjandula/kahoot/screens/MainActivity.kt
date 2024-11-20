package es.iesjandula.kahoot.screens

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import es.iesjandula.kahoot.R
import es.iesjandula.kahoot.database.PreguntaApp

class MainActivity : AppCompatActivity() {
    private val app: PreguntaApp by lazy {
        application as PreguntaApp
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.appBar)
        setSupportActionBar(toolbar)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnJugar = findViewById<Button>(R.id.btnJugar)

        app.database.preguntaDao().getAllLive().observe(this) { listado ->

            btnJugar.setOnClickListener {
                val numeroDePreguntas = listado.size

                if (numeroDePreguntas < 8) {
                    val mensaje = if (numeroDePreguntas == 1) {
                        "Hay $numeroDePreguntas pregunta, es necesario 8 como mínimo"
                    } else {
                        "Hay $numeroDePreguntas preguntas, es necesario 8 como mínimo"
                    }
                    obtenerToast(mensaje)
                } else {
                    val intent = Intent(this, JugarActivity::class.java)
                    startActivity(intent)
                }

            }
        }
    }

    private fun obtenerToast(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.actionconfigurar -> {
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
