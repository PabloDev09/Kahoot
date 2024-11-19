package es.iesjandula.kahoot.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//Con Entity le estamos indicando en Room que es una entidad y por tanto lo tendrá que convertir en una tabla
@Entity (tableName = "Pregunta")
data class Pregunta (
   @PrimaryKey(autoGenerate = true)
   //Hay que inicializarlo a 0 para que luego no se lo tengas que pasar al registro
   @ColumnInfo(name = "id_pregunta") val idPregunta: Int=0,
   @ColumnInfo(name = "tx_pregunta") val txPregunta: String,
   @ColumnInfo(name = "respuesta1") val respuesta1: String,
   @ColumnInfo(name = "respuesta2") val respuesta2: String,
   @ColumnInfo(name = "respuesta3") val respuesta3: String,
   @ColumnInfo(name = "respuesta4") val respuesta4: String,
   @ColumnInfo(name = "referenciaRespuestaCorrecta")  val referenciaRespuestaCorrecta: Int
)
