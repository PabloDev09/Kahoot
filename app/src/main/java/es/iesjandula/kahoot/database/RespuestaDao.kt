package es.iesjandula.kahoot.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import es.iesjandula.kahoot.models.Respuesta
import es.iesjandula.kahoot.models.Pregunta


@Dao
interface RespuestaDao {
    @Insert
    suspend fun insertRespuesta(respuesta: Respuesta)

    @Query("SELECT * FROM Respuesta WHERE idPregunta = :idPregunta")
    suspend fun getRespuestasByPregunta(idPregunta: Int): List<Respuesta>

    @Query("SELECT COUNT(*) FROM Respuesta WHERE esCorrecta = 1")
    suspend fun contarRespuestasCorrectas(): Int
}