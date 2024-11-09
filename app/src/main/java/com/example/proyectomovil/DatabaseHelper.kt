package com.example.proyectomovil

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "FormResponses.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "responses"

        // Columns
        private const val COLUMN_ID = "id"
        private const val COLUMN_RESPUESTA1 = "respuesta1"
        private const val COLUMN_RESPUESTA2 = "respuesta2"
        private const val COLUMN_RESPUESTA3 = "respuesta3"
        private const val COLUMN_RESPUESTA4 = "respuesta4"
        private const val COLUMN_RESPUESTA5 = "respuesta5"
        private const val COLUMN_TIMESTAMP = "timestamp"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_RESPUESTA1 TEXT,
                $COLUMN_RESPUESTA2 TEXT,
                $COLUMN_RESPUESTA3 TEXT,
                $COLUMN_RESPUESTA4 TEXT,
                $COLUMN_RESPUESTA5 TEXT,
                $COLUMN_TIMESTAMP DATETIME DEFAULT CURRENT_TIMESTAMP
            )
        """.trimIndent()

        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertResponse(
        respuesta1: String,
        respuesta2: String,
        respuesta3: String,
        respuesta4: String,
        respuesta5: String
    ): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_RESPUESTA1, respuesta1)
            put(COLUMN_RESPUESTA2, respuesta2)
            put(COLUMN_RESPUESTA3, respuesta3)
            put(COLUMN_RESPUESTA4, respuesta4)
            put(COLUMN_RESPUESTA5, respuesta5)
        }

        return db.insert(TABLE_NAME, null, values)
    }

    fun getAllResponses(): List<FormResponse> {
        val responses = mutableListOf<FormResponse>()
        val db = this.readableDatabase
        val cursor = db.query(
            TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            "$COLUMN_TIMESTAMP DESC"
        )

        with(cursor) {
            while (moveToNext()) {
                val response = FormResponse(
                    id = getInt(getColumnIndexOrThrow(COLUMN_ID)),
                    respuesta1 = getString(getColumnIndexOrThrow(COLUMN_RESPUESTA1)),
                    respuesta2 = getString(getColumnIndexOrThrow(COLUMN_RESPUESTA2)),
                    respuesta3 = getString(getColumnIndexOrThrow(COLUMN_RESPUESTA3)),
                    respuesta4 = getString(getColumnIndexOrThrow(COLUMN_RESPUESTA4)),
                    respuesta5 = getString(getColumnIndexOrThrow(COLUMN_RESPUESTA5))
                )
                responses.add(response)
            }
        }
        cursor.close()
        return responses
    }
}

