package es.iesjandula.kahoot.screens


import android.annotation.SuppressLint
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
import androidx.lifecycle.lifecycleScope
import es.iesjandula.kahoot.R
import es.iesjandula.kahoot.models.Pregunta
import es.iesjandula.kahoot.database.PreguntaDb
import kotlinx.coroutines.launch

class ConfigurarActivity : AppCompatActivity() {
    private lateinit var database : PreguntaDb

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContentView(R.layout.activity_configurar)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.configurar)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        database = PreguntaDb.getDatabase(this)

        val editTextList: List<EditText> = listOf(
            findViewById(R.id.etPregunta),
            findViewById(R.id.etRespuesta1),
            findViewById(R.id.etRespuesta2),
            findViewById(R.id.etRespuesta3),
            findViewById(R.id.etRespuesta4),
            findViewById(R.id.etRespuestaCorrecta)
        )

        val btnSalir = findViewById<Button>(R.id.btnSalir)
        val btnVer = findViewById<Button>(R.id.btnVer)
        val btnGuardarPregunta = findViewById<Button>(R.id.btnGuardar)

        val tvNumeroPreguntas = findViewById<TextView>(R.id.tvNumeroPreguntasCreadas)

        database.preguntaDao().getAllLive().observe(this)
        {
            listado -> tvNumeroPreguntas.text = "Preguntas creadas: ${listado.size}"
            btnVer.setOnClickListener {
                if(listado.isEmpty())
                {
                    Toast.makeText(this, "No hay preguntas creadas", Toast.LENGTH_LONG).show()
                }
                else
                {
                    startActivity(Intent(this, ConsultarActivity::class.java))
                }

            }

        }

        btnSalir.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }


        btnGuardarPregunta.setOnClickListener {
            if (verificarCampos(editTextList, this)) {
                // Crear objeto pregunta
                val prg = asignarValores(editTextList)

                // Insertar objecto pregunta en la base de datos
                insertarPregunta(prg)

                // Limpiar los textos de los EditText
                limpiarValoresEditView(editTextList)

                // Toast de creado correctamente
                Toast.makeText(this, "Pregunta creada correctamente", Toast.LENGTH_LONG).show()

            }
        }
    }

    private fun insertarPregunta(prg: Pregunta) {
        lifecycleScope.launch{
            database.preguntaDao().insert(prg)
        }
    }

    private fun verificarCampos( valoresEditTextList: List<EditText>, configurarActivity: ConfigurarActivity): Boolean
    {
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

            if (valoresEditTextList[i].text.isNullOrBlank()) {
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

    private fun asignarValores(valoresEditTextList: List<EditText>): Pregunta
    {
        return Pregunta(
            txPregunta = valoresEditTextList[0].toString(),
            respuesta1 =  valoresEditTextList[1].toString(),
            respuesta2 = valoresEditTextList[2].toString(),
            respuesta3 = valoresEditTextList[3].toString(),
            respuesta4 = valoresEditTextList[4].toString(),
            referenciaRespuestaCorrecta = valoresEditTextList[5].toString().toInt()
        )
    }

    private fun limpiarValoresEditView(valoresEditTextList: List<EditText>)
    {
        // Limpiar todos los EditText
        for (editText in valoresEditTextList) {
            editText .text.clear()
        }
    }


}



