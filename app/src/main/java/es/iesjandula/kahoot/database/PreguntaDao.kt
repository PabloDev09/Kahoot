package es.iesjandula.kahoot.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import es.iesjandula.kahoot.models.Pregunta


@Dao
interface PreguntaDao {
    @Query("Select * From Pregunta")
    suspend fun getAll(): List<Pregunta>

    @Query("Select * From Pregunta Where id = :idPerson")
    suspend fun getById(idPerson: Int): Pregunta

    @Update
    suspend fun update(pregunta: Pregunta)

    @Insert
    suspend fun insert(preguntas: Pregunta)

    @Delete
    suspend fun delete(pregunta: Pregunta)
}