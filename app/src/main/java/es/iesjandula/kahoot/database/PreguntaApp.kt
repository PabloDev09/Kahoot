package es.iesjandula.kahoot.database

import android.app.Application
import android.util.Log
import androidx.room.Room

class PreguntaApp:Application() {
    val database: PreguntaDb by lazy {
        PreguntaDb.getDatabase(this)
    }


}