package es.iesjandula.kahoot.screens

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import es.iesjandula.kahoot.R
import es.iesjandula.kahoot.database.PreguntaApp
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val app = PreguntaApp
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnConfigurar = findViewById<Button>(R.id.btnConfigurar)
        val btnJugar = findViewById<Button>(R.id.btnJugar)

        btnConfigurar.setOnClickListener {
            val intent = Intent(this, ConfigurarActivity::class.java)
            startActivity(intent)
        }

        btnJugar.setOnClickListener {
            lifecycleScope.launch {
                val numeroDePreguntas = obtenerPreguntas()

                if (numeroDePreguntas < 8) {
                    val mensaje = if (numeroDePreguntas == 1)
                    {
                        "Hay $numeroDePreguntas pregunta, es necesario 5 como mínimo"
                    } else {
                        "Hay $numeroDePreguntas preguntas, es necesario 5 como mínimo"
                    }
                    Toast.makeText(this@MainActivity, mensaje, Toast.LENGTH_LONG).show()
                }
            else{
                    val intent = Intent(this@MainActivity, JugarActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }


    private suspend fun obtenerPreguntas(): Int
    {
        return app.room.preguntaDao().getAll().size
    }
}
