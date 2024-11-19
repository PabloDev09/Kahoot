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
    private val app = PreguntaApp

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

        val btnConfigurar = findViewById<Button>(R.id.btnConfigurar)
        val btnJugar = findViewById<Button>(R.id.btnJugar)

        btnConfigurar.setOnClickListener {

        }

        Log.d(tag, "Antes de darle click")

        btnJugar.setOnClickListener {

        }
    }

    private fun obtenerPreguntas(): Int {
        var numeroPreguntas = 0
        lifecycleScope.launch {
            numeroPreguntas = app.room.preguntaDao().getAll().size
        }
        return numeroPreguntas
    }

    private fun validarNumeroPreguntas()
    {
        val numeroDePreguntas = obtenerPreguntas()

        if (numeroDePreguntas < 8) {
            val mensaje = if (numeroDePreguntas == 1) {
                "Hay $numeroDePreguntas pregunta, es necesario 5 como mínimo"
            } else {
                "Hay $numeroDePreguntas preguntas, es necesario 5 como mínimo"
            }
            Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
        } else {
            val intent = Intent(this, JugarActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.actionconfigurar -> {
                validarNumeroPreguntas()
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
