import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.iesjandula.kahoot.R
import es.iesjandula.kahoot.models.Pregunta
import es.iesjandula.kahoot.recycler.PreguntaViewHolder

class PreguntasAdapter(private val context: Context, private val preguntas: List<Pregunta>
) : RecyclerView.Adapter<PreguntaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreguntaViewHolder {
        // Inflar la vista del ítem
        val view = LayoutInflater.from(context).inflate(R.layout.view_pregunta_recycler, parent, false)
        return PreguntaViewHolder(view)
    }

    override fun onBindViewHolder(holder: PreguntaViewHolder, position: Int) {
        // Asignar los datos al ViewHolder
        val pregunta = preguntas[position]
        holder.preguntaCard.findViewById<TextView>(R.id.tvTitle).append( " ${preguntas[position].idPregunta}")
        holder.preguntaCard.findViewById<TextView>(R.id.tvPregunta).text = preguntas[position].txPregunta
        holder.preguntaCard.findViewById<TextView>(R.id.tvRespuesta1).text = preguntas[position].respuesta1
        holder.preguntaCard.findViewById<TextView>(R.id.tvRespuesta2).text = preguntas[position].respuesta2
        holder.preguntaCard.findViewById<TextView>(R.id.tvRespuesta3).text = preguntas[position].respuesta3
        holder.preguntaCard.findViewById<TextView>(R.id.tvRespuesta4).text = preguntas[position].respuesta4
    }

    override fun getItemCount(): Int {
        return preguntas.size
    }


}
