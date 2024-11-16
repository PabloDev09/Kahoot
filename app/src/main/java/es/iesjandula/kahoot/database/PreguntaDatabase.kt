package com.example.bbdd

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import es.iesjandula.kahoot.models.PreguntaModel


class PreguntaDatabase(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION)
{

    companion object
    {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME ="kahoot.db"
        private const val  TBL_PREGUNTA = "tbl_pregunta"
        private const val PREGUNTA = "pregunta"
        private const val RESPUESTAPRIMERO = "respuesta_primero"
        private const val RESPUESTASEGUNDA = "respuesta_segunda"
        private const val RESPUESTATERCERA = "respuesta_tercero"
        private const val RESPUESTACUARTA = "respuesta_cuatro"
        private const val REFERENCIARESPUESTACORRECTA = "referencia_respuesta_correcta"
    }

    override fun onCreate(db: SQLiteDatabase?)
    {
        val createTblStudent = ("CREATE TABLE "+ TBL_PREGUNTA+"("
                + PREGUNTA+ " TEXT PRIMARY KEY,"+RESPUESTAPRIMERO+ " TEXT,"
                + RESPUESTASEGUNDA+ " TEXT," +RESPUESTATERCERA+" TEXT, "
                + RESPUESTACUARTA+" TEXT,"
                + REFERENCIARESPUESTACORRECTA + " INTEGER" +
                ")"
                )
        //Ejecutamos la setencia
        db?.execSQL(createTblStudent)
    }

    override fun onUpgrade(db: SQLiteDatabase?, old: Int, new: Int)
    {
        db!!.execSQL("DROP TABLE IF EXISTS $TBL_PREGUNTA")
        onCreate(db)
    }

    fun insertStudent(prg: PreguntaModel): Long
    {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(PREGUNTA, prg.pregunta)
        contentValues.put(RESPUESTAPRIMERO, prg.respuestaPrimera)
        contentValues.put(RESPUESTASEGUNDA, prg.respuestaSegunda)
        contentValues.put(RESPUESTAPRIMERO, prg.respuestaTercera)
        contentValues.put(RESPUESTASEGUNDA, prg.respuestaCuarta)
        contentValues.put(REFERENCIARESPUESTACORRECTA, prg.referenciaRespuestaCorrecta)

        val sucess = db.insert(TBL_PREGUNTA, null, contentValues)
        db.close()
        return sucess
    }

    @SuppressLint("Range")
    fun getAllStudent () : ArrayList<PreguntaModel>
    {
        val prgList: ArrayList<PreguntaModel> = ArrayList()
        val selectQuery = "SELECT * FROM $TBL_PREGUNTA"
        val db = this.readableDatabase

        val cursor: Cursor?

        try
        {
           cursor = db.rawQuery(selectQuery, null)
        }
        catch (e: Exception)
        {
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var pregunta: String
        var respuestaPrimera: String
        var respuestaSegunda: String
        var respuestaTercera: String
        var respuestaCuarta: String
        var referenciaRespuestaCorrecta: Int

        if (cursor.moveToFirst())
        {
            do {
                pregunta = cursor.getString(cursor.getColumnIndex("pregunta"))
                respuestaPrimera = cursor.getString(cursor.getColumnIndex("respuesta_primero"))
                respuestaSegunda = cursor.getString(cursor.getColumnIndex("respuesta_segunda"))
                respuestaTercera = cursor.getString(cursor.getColumnIndex("respuesta_tercero"))
                respuestaCuarta = cursor.getString(cursor.getColumnIndex("respuesta_cuatro "))
                referenciaRespuestaCorrecta = cursor.getInt(cursor.getColumnIndex("referencia_respuesta_correcta"))

                val prg = PreguntaModel(
                    pregunta = pregunta,
                    respuestaPrimera = respuestaPrimera,
                    respuestaSegunda = respuestaSegunda,
                    respuestaTercera = respuestaTercera,
                    respuestaCuarta = respuestaCuarta,
                    referenciaRespuestaCorrecta = referenciaRespuestaCorrecta
                )
                prgList.add(prg)
            } while (cursor.moveToNext())
        }
        return prgList

    }
}