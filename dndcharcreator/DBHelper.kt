package edu.msudenver.dndcharcreator

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.io.Serializable
import java.text.SimpleDateFormat

class DBHelper (context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION), Serializable {

    companion object {
        const val DATABASE_NAME = "CharacterCreator.db"
        const val DATABASE_VERSION = 1

    }

    override fun onCreate(db: SQLiteDatabase?) {
        // create the table
        db?.execSQL("""
            CREATE TABLE characters ( 
                date     TEXT NOT NULL, 
                level    INTEGER NOT NULL,
                value    DOUBLE NOT NULL)
        """)

        /*
        db?.execSQL("""
            INSERT INTO characters VALUES
                ("2022-07-20", 0, .5)
        """)*/
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // drop the table
        db?.execSQL("""
            DROP TABLE IF EXISTS characters
        """)

        // then call "onCreate" again
        onCreate(db)
    }
}