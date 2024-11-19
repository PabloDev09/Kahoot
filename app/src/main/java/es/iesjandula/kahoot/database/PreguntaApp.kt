package es.iesjandula.kahoot.database

import android.app.Application
import androidx.room.Room

class PreguntaApp:Application() {
    companion object {
        lateinit var room: PreguntaDb
    }

    override fun onCreate() {
        super.onCreate()
        room = Room.databaseBuilder(applicationContext, PreguntaDb::class.java, "pregunta")
            .build()
    }

}