package es.iesjandula.kahoot.screens

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import es.iesjandula.kahoot.R

class ResultadosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultados)


        val respuestasCorrectas = intent.getIntExtra("respuestasCorrectas", 0)


        val tvResultadoFinal = findViewById<TextView>(R.id.tvResultadoFinal)
        tvResultadoFinal.text = "Respuestas correctas: $respuestasCorrectas /5"

        val btnVolverMenu = findViewById<Button>(R.id.btnVolverMenu)

        // Establecer el listener para el botón
        btnVolverMenu.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            // Finalizar la actividad actual (ResultadosActivity)
            finish()
        }
    }
}

