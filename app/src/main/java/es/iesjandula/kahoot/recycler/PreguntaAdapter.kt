import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import es.iesjandula.kahoot.R
import es.iesjandula.kahoot.database.PreguntaDb
import es.iesjandula.kahoot.models.Pregunta
import es.iesjandula.kahoot.recycler.PreguntaViewHolder
import es.iesjandula.kahoot.screens.ConsultarActivity
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PreguntasAdapter(private val context: Context, private val preguntas: List<Pregunta>
) : RecyclerView.Adapter<PreguntaViewHolder>() {
    private var database = PreguntaDb.getDatabase(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreguntaViewHolder {
        // Inflar la vista del ítem
        val view =
            LayoutInflater.from(context).inflate(R.layout.view_pregunta_recycler, parent, false)
        return PreguntaViewHolder(view)
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onBindViewHolder(holder: PreguntaViewHolder, position: Int) {
        val pregunta = preguntas[position]

        // Configurar las vistas
        holder.preguntaCard.findViewById<TextView>(R.id.tvTitle).append(" ${position + 1}")
        holder.preguntaCard.findViewById<TextView>(R.id.tvPregunta).text = pregunta.txPregunta
        holder.preguntaCard.findViewById<TextView>(R.id.tvRespuesta1).append(" ${pregunta.respuesta1}")
        holder.preguntaCard.findViewById<TextView>(R.id.tvRespuesta2).append(" ${pregunta.respuesta2}")
        holder.preguntaCard.findViewById<TextView>(R.id.tvRespuesta3).append(" ${pregunta.respuesta3}")
        holder.preguntaCard.findViewById<TextView>(R.id.tvRespuesta4).append(" ${pregunta.respuesta4}")


        holder.preguntaCard.findViewById<TextView>(R.id.btnBorrar).setOnClickListener {

            GlobalScope.launch {

                database.preguntaDao().delete(pregunta)

                (context as ConsultarActivity).runOnUiThread {
                    val mutablePreguntas = preguntas.toMutableList()
                    mutablePreguntas.removeAt(position)

                    Toast.makeText(context, "Pregunta borrada con exito", Toast.LENGTH_LONG).show()

                    (preguntas as MutableList).clear()
                    preguntas.addAll(mutablePreguntas)
                    notifyItemRemoved(position)
                    notifyItemRangeChanged(position, preguntas.size)
                }
            }
        }
    }




    override fun getItemCount(): Int {
        return preguntas.size
    }
}