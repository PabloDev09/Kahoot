package es.iesjandula.kahoot.screens

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import es.iesjandula.kahoot.R
import es.iesjandula.kahoot.database.PreguntaApp
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val tag = "MainActivityTag"

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

        val btnVer = findViewById<Button>(R.id.btnVer)


    }

    private suspend fun obtenerPreguntas(): Int {
        return PreguntaApp.room.preguntaDao().getAll().size
    }

    private fun validarNumeroPreguntas() {
        lifecycleScope.launch {
            try {
                val numeroDePreguntas = obtenerPreguntas()

                if (numeroDePreguntas < 5) { // Según el mensaje, debería ser 5 como mínimo
                    val mensaje = if (numeroDePreguntas == 1) {
                        "Hay $numeroDePreguntas pregunta, es necesario 5 como mínimo"
                    } else {
                        "Hay $numeroDePreguntas preguntas, es necesario 5 como mínimo"
                    }
                    Toast.makeText(this@MainActivity, mensaje, Toast.LENGTH_LONG).show()
                } else {
                    val intent = Intent(this@MainActivity, JugarActivity::class.java)
                    startActivity(intent)
                }
            } catch (e: Exception) {
                Log.e(tag, "Error al obtener preguntas: ${e.message}")
                Toast.makeText(this@MainActivity, "Error al validar preguntas.", Toast.LENGTH_SHORT).show()
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
            R.id.actionjugar -> {
                validarNumeroPreguntas()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
