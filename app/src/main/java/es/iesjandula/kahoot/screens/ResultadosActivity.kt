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

        // Obtener las respuestas correctas desde el Intent
        val respuestasCorrectas = intent.getIntExtra("respuestasCorrectas", 0)

        // Mostrar el resultado final
        val tvResultadoFinal = findViewById<TextView>(R.id.tvResultadoFinal)
        tvResultadoFinal.text = "Respuestas correctas: $respuestasCorrectas /5"

        val btnVolverMenu = findViewById<Button>(R.id.btnVolverMenu)

        // Establecer el listener para el botón
        btnVolverMenu.setOnClickListener {
            // Crear un Intent para iniciar la actividad MainActivity
            val intent = Intent(this, MainActivity::class.java) // Aquí puedes cambiar 'MainActivity' por la actividad deseada
            startActivity(intent) // Iniciar la actividad
            finish() // Finalizar la actividad actual (ResultadosActivity)
        }
    }
}

