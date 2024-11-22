package es.iesjandula.kahoot.screens

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import es.iesjandula.kahoot.R
import es.iesjandula.kahoot.database.PreguntaDb
import es.iesjandula.kahoot.models.Pregunta
import es.iesjandula.kahoot.models.Respuesta
import es.iesjandula.kahoot.screens.ResultadosActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class JugarActivity : AppCompatActivity() {

    private lateinit var database: PreguntaDb
    private val respuestasCorrectas = mutableListOf<Pregunta>() // Lista para almacenar respuestas correctas
    private var preguntaActualIndex = 0 // Para controlar la pregunta actual
    private lateinit var preguntas: List<Pregunta> // Lista de preguntas

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jugar)

        // Inicializamos la base de datos
        database = PreguntaDb.getDatabase(this)

        // Cargar las preguntas desde la base de datos
        cargarPreguntas()

        val valoresBtnList: List<Button> = listOf(
            findViewById(R.id.btnRespuesta1),
            findViewById(R.id.btnRespuesta2),
            findViewById(R.id.btnRespuesta3),
            findViewById(R.id.btnRespuesta4)
        )
        val btnSalirJugar = findViewById<Button>(R.id.btnSalirJugar)
        val btnContestarJugar = findViewById<Button>(R.id.btnContestarJugar)

        var respuestaSeleccionada = 0 // Respuesta seleccionada por el usuario

        // Manejar la selección de respuestas
        for (i in valoresBtnList.indices) {
            valoresBtnList[i].setOnClickListener {
                respuestaSeleccionada = i + 1 // Identifica la respuesta seleccionada
                // Cambiar color al botón seleccionado
                for (j in valoresBtnList.indices) {
                    if (j + 1 == respuestaSeleccionada) {
                        valoresBtnList[j].setBackgroundColor(
                            ContextCompat.getColor(this, R.color.colortemalight)
                        )
                    } else {
                        // Cambiar color a los demás botones
                        valoresBtnList[j].setBackgroundColor(
                            ContextCompat.getColor(this, R.color.colortema)
                        )
                    }
                }
            }
        }

        btnSalirJugar.setOnClickListener {
            // Si el usuario quiere salir, se redirige al MainActivity.
            startActivity(Intent(this, MainActivity::class.java))
            finish()  // Asegurarse de que la actividad actual se cierre.
        }

        btnContestarJugar.setOnClickListener {
            if (respuestaSeleccionada == 0) {
                Toast.makeText(this, "Selecciona una respuesta", Toast.LENGTH_SHORT).show()
            } else {
                val preguntaActual = preguntas[preguntaActualIndex] // Obtener la pregunta actual
                val esCorrecta = respuestaSeleccionada == preguntaActual.referenciaRespuestaCorrecta // Compara la respuesta con la correcta

                // Si la respuesta es correcta, agregarla a la lista de respuestas correctas
                if (esCorrecta) {
                    respuestasCorrectas.add(preguntaActual)
                }

                // Guardar la respuesta en la base de datos usando Coroutines
                val nuevaRespuesta = Respuesta(
                    idPregunta = preguntaActual.idPregunta,
                    respuestaSeleccionada = respuestaSeleccionada.toString(),
                    esCorrecta = esCorrecta
                )

                // Usamos la coroutine para insertar en segundo plano
                lifecycleScope.launch {
                    withContext(Dispatchers.IO) {
                        try {
                            // Inserción en la base de datos en un hilo de fondo
                            database.respuestaDao().insertRespuesta(nuevaRespuesta)
                        } catch (e: Exception) {
                            e.printStackTrace()  // Manejo de errores si ocurre algún problema con la inserción
                        }
                    }
                }

                // Mover a la siguiente pregunta
                preguntaActualIndex++

                // Si se terminó el juego (5 preguntas), redirigir a la pantalla de resultados
                if (preguntaActualIndex >= 5) {  // Cambiar al 5, ya que solo quieres mostrar las 5 primeras preguntas
                    val totalRespuestasCorrectas = respuestasCorrectas.size
                    // Redirigir a la pantalla de resultados
                    val intent = Intent(this, ResultadosActivity::class.java)
                    intent.putExtra("respuestasCorrectas", totalRespuestasCorrectas)
                    startActivity(intent)
                    finish()  // Cerrar la actividad después de mostrar los resultados
                } else {
                    // Desmarcar respuesta anterior si hay alguna seleccionada
                    for (j in valoresBtnList.indices) {
                        valoresBtnList[j].setBackgroundColor(
                            ContextCompat.getColor(this, R.color.colortema)
                        )
                    }

                    // Mostrar la siguiente pregunta
                    mostrarPregunta(preguntaActualIndex)
                }
            }
        }
    }

    private fun cargarPreguntas() {
        lifecycleScope.launch {
            preguntas = withContext(Dispatchers.IO) {
                database.preguntaDao().getAllPreguntas().take(5)  // Solo tomar las primeras 5 preguntas
            }
            if (preguntas.isNotEmpty()) {
                mostrarPregunta(preguntaActualIndex) // Mostrar la primera pregunta si se han cargado correctamente
            } else {
                Toast.makeText(this@JugarActivity, "No hay preguntas disponibles", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@JugarActivity, MainActivity::class.java))
                finish()
            }
        }
    }

    private fun mostrarPregunta(index: Int) {
        val pregunta = preguntas[index]
        val valoresBtnList: List<Button> = listOf(
            findViewById(R.id.btnRespuesta1),
            findViewById(R.id.btnRespuesta2),
            findViewById(R.id.btnRespuesta3),
            findViewById(R.id.btnRespuesta4)
        )

        // Configurar el texto de las respuestas
        findViewById<TextView>(R.id.tvPreguntaActual).text = pregunta.txPregunta
        valoresBtnList[0].text = pregunta.respuesta1
        valoresBtnList[1].text = pregunta.respuesta2
        valoresBtnList[2].text = pregunta.respuesta3
        valoresBtnList[3].text = pregunta.respuesta4
    }
}
