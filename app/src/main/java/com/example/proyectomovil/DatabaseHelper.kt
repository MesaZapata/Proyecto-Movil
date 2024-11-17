package com.example.proyectomovil

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "FormResponses.db"
        private const val DATABASE_VERSION = 1

        // Tabla 1: Respuestas
        private const val TABLE_RESPONSES = "responses"
        private const val COLUMN_ID = "id"
        private const val COLUMN_RESPUESTA1 = "respuesta1"
        private const val COLUMN_RESPUESTA2 = "respuesta2"
        private const val COLUMN_RESPUESTA3 = "respuesta3"
        private const val COLUMN_RESPUESTA4 = "respuesta4"
        private const val COLUMN_RESPUESTA5 = "respuesta5"
        private const val COLUMN_TIMESTAMP = "timestamp"

        // Tabla 2: Lenguaje
        private const val TABLE_LANGUAGE = "language"
        private const val COLUMN_LANGUAGE_ID = "language_id"

        // Tabla 3: Roles
        private const val TABLE_ROLES = "roles"
        private const val COLUMN_ROLES_ID = "roles_id"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createResponsesTable = """
            CREATE TABLE $TABLE_RESPONSES (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_RESPUESTA1 TEXT,
                $COLUMN_RESPUESTA2 TEXT,
                $COLUMN_RESPUESTA3 TEXT,
                $COLUMN_RESPUESTA4 TEXT,
                $COLUMN_RESPUESTA5 TEXT,
                $COLUMN_TIMESTAMP DATETIME DEFAULT CURRENT_TIMESTAMP
            )
        """.trimIndent()

        val createLanguageTable = """
            CREATE TABLE $TABLE_LANGUAGE (
                $COLUMN_LANGUAGE_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_RESPUESTA2 TEXT,
                $COLUMN_TIMESTAMP DATETIME DEFAULT CURRENT_TIMESTAMP
            )
        """.trimIndent()

        val createRolesTable = """
            CREATE TABLE $TABLE_ROLES (
                $COLUMN_ROLES_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_RESPUESTA4 TEXT,
                $COLUMN_TIMESTAMP DATETIME DEFAULT CURRENT_TIMESTAMP
            )
        """.trimIndent()

        db.execSQL(createResponsesTable)
        db.execSQL(createLanguageTable)
        db.execSQL(createRolesTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_RESPONSES")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_LANGUAGE")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_ROLES")
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
        return db.insert(TABLE_RESPONSES, null, values)
    }


    fun insertLanguage(respuesta2: String): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_RESPUESTA2, respuesta2)
        }
        return db.insert(TABLE_LANGUAGE, null, values)
    }


    fun insertRoles(respuesta4: String): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_RESPUESTA4, respuesta4)
        }
        return db.insert(TABLE_ROLES, null, values)
    }

    fun getAllResponses(): List<FormResponse> {
        val responses = mutableListOf<FormResponse>()
        val db = this.readableDatabase
        val cursor = db.query(
            TABLE_RESPONSES,
            null,
            null,
            null,
            null,
            null,
            "$COLUMN_TIMESTAMP DESC"
        )

        cursor.use {
            while (it.moveToNext()) {
                val response = FormResponse(
                    id = it.getInt(it.getColumnIndexOrThrow(COLUMN_ID)),
                    respuesta1 = it.getString(it.getColumnIndexOrThrow(COLUMN_RESPUESTA1)),
                    respuesta2 = it.getString(it.getColumnIndexOrThrow(COLUMN_RESPUESTA2)),
                    respuesta3 = it.getString(it.getColumnIndexOrThrow(COLUMN_RESPUESTA3)),
                    respuesta4 = it.getString(it.getColumnIndexOrThrow(COLUMN_RESPUESTA4)),
                    respuesta5 = it.getString(it.getColumnIndexOrThrow(COLUMN_RESPUESTA5))
                )
                responses.add(response)
            }
        }
        return responses
    }

    fun getAllLanguages(): List<FormLanguage> {
        val languages = mutableListOf<FormLanguage>()
        val db2 = this.readableDatabase
        val cursor2 = db2.query(
            TABLE_LANGUAGE,
            null,
            null,
            null,
            null,
            null,
            "$COLUMN_TIMESTAMP DESC"
        )

        cursor2.use {
            while (it.moveToNext()) {
                val la = FormLanguage(
                    id = it.getInt(it.getColumnIndexOrThrow(COLUMN_LANGUAGE_ID)),
                    respuesta2 = it.getString(it.getColumnIndexOrThrow(COLUMN_RESPUESTA2))
                )
                languages.add(la)
            }
        }
        return languages
    }

    fun getAllRoles(): List<FormRoles> {
        val roles = mutableListOf<FormRoles>()
        val db3 = this.readableDatabase
        val cursor3 = db3.query(
            TABLE_LANGUAGE,
            null,
            null,
            null,
            null,
            null,
            "$COLUMN_TIMESTAMP DESC"
        )

        cursor3.use {
            while (it.moveToNext()) {
                val ro = FormRoles(
                    id = it.getInt(it.getColumnIndexOrThrow(COLUMN_ROLES_ID)),
                    respuesta4 = it.getString(it.getColumnIndexOrThrow(COLUMN_RESPUESTA4))
                )
                roles.add(ro)
            }
        }
        return roles
    }
}
