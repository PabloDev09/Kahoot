package es.iesjandula.kahoot.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import es.iesjandula.kahoot.models.Pregunta
import es.iesjandula.kahoot.models.Respuesta

// Aquí creamos la BD
@Database(
    // Lista de entidades que va a tener nuestra BD
    entities = [Pregunta::class, Respuesta::class],
    version = 2
)
abstract class PreguntaDb : RoomDatabase() {
    // Las funciones que nos van a devolver los Dao que hemos implementado
    abstract fun preguntaDao(): PreguntaDao
    abstract fun respuestaDao(): RespuestaDao

    companion object {
        @Volatile
        private var INSTANCE: PreguntaDb? = null

        fun getDatabase(context: Context): PreguntaDb {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PreguntaDb::class.java,
                    "pregunta"
                )
                    // Utilizamos la opción para destruir la base de datos si hay cambios en el esquema
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
