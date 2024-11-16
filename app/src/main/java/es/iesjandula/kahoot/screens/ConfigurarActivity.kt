package es.iesjandula.kahoot.screens

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bbdd.PreguntaDatabase
import es.iesjandula.kahoot.R
import es.iesjandula.kahoot.models.PreguntaModel

class ConfigurarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_configurar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.configurar)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<TextView>(R.id.tvNumeroPreguntasCreadas).append(PreguntaDatabase(this).getAllStudent().size.toString())
        val valoresEditTextList: List<EditText> = listOf(
            findViewById(R.id.etPreguntaInput),
            findViewById(R.id.etRespuesta1Input),
            findViewById(R.id.etRespuesta2Input),
            findViewById(R.id.etRespuesta3Input),
            findViewById(R.id.etRespuesta4Input),
            findViewById(R.id.etRespuestaCorrectaInput)
        )
        val btnGuardarPregunta = findViewById<Button>(R.id.btnGuardarButton)

        btnGuardarPregunta.setOnClickListener {
            if (verificarCampos(valoresEditTextList, this)) {
                // Crear objeto pregunta para insertar en la BBDD
                val prg: PreguntaModel = PreguntaModel()

                // Asignar los valores a la pregunta a traves de la lista de EditText
                asignarValores(prg, valoresEditTextList)

                // Insertar la pregunta
                PreguntaDatabase(this).insertStudent(prg)

                // Limpiar los textos de los EditText
                limpiarValoresEditView(valoresEditTextList)

                // Toast de creado correctamente
                Toast.makeText(this, "Pregunta creada correctamente", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun limpiarValoresEditView(valoresEditTextList: List<EditText>) {
        // Limpiar todos los EditText
        for (i in 0..<valoresEditTextList.size) {
            valoresEditTextList.get(i).text.clear()
        }
    }

    private fun asignarValores(prg: PreguntaModel, valoresEditTextList: List<EditText>, ) {
        prg.pregunta = valoresEditTextList.get(0).text.toString()
        prg.respuestaPrimera = valoresEditTextList.get(1).text.toString()
        prg.respuestaSegunda = valoresEditTextList.get(2).text.toString()
        prg.respuestaTercera = valoresEditTextList.get(3).text.toString()
        prg.respuestaCuarta = valoresEditTextList.get(4).text.toString()
        prg.referenciaRespuestaCorrecta = valoresEditTextList.get(5).text.toString().toInt()
    }


    private fun verificarCampos(
        valoresEditTextList: List<EditText>,
        configurarActivity: ConfigurarActivity
    ): Boolean {
        val campo5 = valoresEditTextList.last().text.toString()

        if (campo5.isBlank() || campo5.toIntOrNull() !in 1..4) {
            Toast.makeText(
                configurarActivity,
                "El campo 5 no tiene un número entre 1 y 4",
                Toast.LENGTH_LONG
            ).show()
            return false
        }

        for (i in 0..<valoresEditTextList.size - 1) {

            if (valoresEditTextList.get(i).text.isNullOrBlank()) {
                Toast.makeText(
                    configurarActivity,
                    "El campo ${i + 1} está vacio",
                    Toast.LENGTH_LONG
                ).show()
                return false
            }
        }
        return true
    }
}



