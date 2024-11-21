package es.iesjandula.kahoot.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Respuesta")
data class Respuesta(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val idPregunta: Int,
    val respuestaSeleccionada: String,
    val esCorrecta: Boolean // Este campo indica si la respuesta fue correcta o no
)
