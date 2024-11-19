package es.iesjandula.kahoot.database

import android.app.Application
import android.util.Log
import androidx.room.Room

class PreguntaApp:Application() {
    companion object {
        val room: PreguntaDb by lazy {
            Room.databaseBuilder(
                appContext,
                PreguntaDb::class.java,
                "Pregunta"
            ).build()
        }
        lateinit var appContext: Application
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this
        Log.d("PreguntaApp", "Application context set.")
    }


}