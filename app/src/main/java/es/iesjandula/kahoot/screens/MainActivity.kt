package es.iesjandula.kahoot.screens

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bbdd.PreguntaDatabase
import es.iesjandula.kahoot.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var intent: Intent
        val btnConfigurar = findViewById<Button>(R.id.btnConfigurar)
        val btnJugar = findViewById<Button>(R.id.btnJugar)

        btnConfigurar.setOnClickListener{

                intent = Intent(this, ConfigurarActivity::class.java)
                startActivity(intent)
        }

        btnJugar.setOnClickListener{
             if(PreguntaDatabase(this).getAllStudent().size < 5)
             {
                 Toast.makeText(this, "Hay solo ${PreguntaDatabase(this).getAllStudent().size} preguntas, es necesario 5 como mínimo", Toast.LENGTH_LONG).show()
             }
             else
             {
                 intent = Intent(this, JugarActivity::class.java)
                 startActivity(intent)
             }
         }



    }
}