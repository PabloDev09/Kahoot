package es.iesjandula.kahoot.screens

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import es.iesjandula.kahoot.R
import es.iesjandula.kahoot.database.PreguntaDb

class JugarActivity : AppCompatActivity() {
    private lateinit var database: PreguntaDb
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_jugar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.jugar)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        database = PreguntaDb.getDatabase(this)

        val valoresBtnList: List<Button> = listOf(
            findViewById(R.id.btnRespuesta1),
            findViewById(R.id.btnRespuesta2),
            findViewById(R.id.btnRespuesta3),
            findViewById(R.id.btnRespuesta4),
        )
        val btnSalirJugar = findViewById<Button>(R.id.btnSalirJugar)
        val btnContestarJugar = findViewById<Button>(R.id.btnContestarJugar)
        var respuesta = 0

        for (i in valoresBtnList.indices) {
            valoresBtnList[i].setOnClickListener {
                respuesta = i + 1 // Identifica la respuesta seleccionada

                for (j in valoresBtnList.indices) {
                    if (j + 1 == respuesta) {
                        // Cambiar color al botón seleccionado
                        valoresBtnList[j].setBackgroundColor(
                            ContextCompat.getColor(this, R.color.moradononormal)
                        )
                    } else {
                        // Cambiar color a los demás botones
                        valoresBtnList[j].setBackgroundColor(
                            ContextCompat.getColor(this, R.color.moradonormal)
                        )
                    }
                }
            }
        }

        btnSalirJugar.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        btnContestarJugar.setOnClickListener {
            if(respuesta == 0)
            {
                Toast.makeText(this, "Selecciona una respuesta", Toast.LENGTH_SHORT).show()
            }
        }
    }
}