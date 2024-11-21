package es.iesjandula.kahoot.screens


import PreguntasAdapter
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.iesjandula.kahoot.R
import es.iesjandula.kahoot.database.PreguntaDb

class ConsultarActivity : AppCompatActivity() {
    private lateinit var database: PreguntaDb

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_consultar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.consultar)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        database = PreguntaDb.getDatabase(this)
        val recyclerViewPregunta = findViewById<RecyclerView>(R.id.preguntasRV)

        val btnSalir = findViewById<Button>(R.id.btnSalir)

        btnSalir.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        database.preguntaDao().getAllLive().observe(this)
        { listado ->
            recyclerViewPregunta.layoutManager = LinearLayoutManager(this)
            recyclerViewPregunta.adapter = PreguntasAdapter(context = this, preguntas = listado)
        }


    }
}



