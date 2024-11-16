package com.example.bbdd

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import es.iesjandula.kahoot.models.PreguntaModel


class SQLiteHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME ="kahoot.db"
        private const val  TBL_PREGUNTA = "tbl_pregunta"
        private const val ID = "id"
        private const val NAME = "name"
        private const val EMAIL = "email"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTblStudent = ("CREATE TABLE "+ TBL_STUDENT+"("
                + ID+ " INTEGER PRIMARY KEY,"+NAME+ " TEXT,"
                + EMAIL+ " TEXT"+")"
                )
        //Ejecutamos la setencia
        db?.execSQL(createTblStudent)
    }

    override fun onUpgrade(db: SQLiteDatabase?, old: Int, new: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TBL_STUDENT")
        onCreate(db)
    }

    fun insertStudent(std: PreguntaModel): Long{
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID, std.id)
        contentValues.put(NAME, std.name)
        contentValues.put(EMAIL, std.email)

        val sucess = db.insert(TBL_PREGUNTA, null, contentValues)
        db.close()
        return sucess

    }

    @SuppressLint("Range")
    fun getAllStudent () : ArrayList<PreguntaModel>{
        val stdList: ArrayList<PreguntaModel> = ArrayList()
        val selectQuery = "SELECT * FROM $TBL_PREGUNTA"
        val db = this.readableDatabase

        val cursor: Cursor?

        try {
           cursor = db.rawQuery(selectQuery, null)
        } catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var id: Int
        var name: String
        var email: String

        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndex("id"))
                name = cursor.getString(cursor.getColumnIndex("name"))
                email = cursor.getString(cursor.getColumnIndex("email"))

                val std = PreguntaModel(id = id, name = "name", email = "email")
                stdList.add(std)
            } while (cursor.moveToNext())
        }
        return stdList

    }
}