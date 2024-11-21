package es.iesjandula.kahoot.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import es.iesjandula.kahoot.models.Pregunta
import es.iesjandula.kahoot.models.Respuesta


@Dao
interface PreguntaDao {
    @Query("Select * From Pregunta")
    fun getAllLive(): LiveData<List<Pregunta>>

    @Query("Select * From Pregunta")
    suspend fun getAll(): List<Pregunta>

    @Query("Select * From Pregunta Where id_pregunta = :idPregunta")
    suspend fun getById(idPregunta: Int): Pregunta

    @Update
    suspend fun update(pregunta: Pregunta)

    @Insert
    suspend fun insert(pregunta: Pregunta)

    @Delete
    suspend fun delete(pregunta: Pregunta)

    @Query("SELECT * FROM Pregunta")
    fun getAllPreguntas(): List<Pregunta>

    @Insert
    fun insertPregunta(pregunta: Pregunta)

    @Update
    fun updatePregunta(pregunta: Pregunta)

    @Insert
    suspend fun insertRespuesta(respuesta: Respuesta)

    @Query("SELECT * FROM Respuesta WHERE idPregunta = :idPregunta")
    suspend fun getRespuestasByPregunta(idPregunta: Int): List<Respuesta>

    @Query("SELECT COUNT(*) FROM Respuesta WHERE esCorrecta = 1")
    suspend fun contarRespuestasCorrectas(): Int
}