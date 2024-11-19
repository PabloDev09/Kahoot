package es.iesjandula.kahoot.database

import androidx.room.Database
import androidx.room.RoomDatabase
import es.iesjandula.kahoot.models.Pregunta

//Aquí creamos la BD
@Database(
    //Podemos poner una lista de entidades que vaya a tener nuestra BD, de ahí que pongamos []
    entities = [Pregunta::class],
    version = 1
)
abstract class PreguntaDb: RoomDatabase() {
    //las funciones que nos van a devolver los Dao que hemos implementado
    abstract fun preguntaDao(): PreguntaDao
}