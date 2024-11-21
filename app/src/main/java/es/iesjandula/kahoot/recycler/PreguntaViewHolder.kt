package es.iesjandula.kahoot.recycler

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.iesjandula.kahoot.R

// Clase ViewHolder
    class PreguntaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val preguntaCard: View = itemView.findViewById(R.id.cardContainerPreguntas)
    }