package edu.msudenver.dndcharcreator

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.io.Serializable


class DBHelper (context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION), Serializable {

    companion object {
        const val DATABASE_NAME = "CharacterCreator.db"
        const val DATABASE_VERSION = 1

    }

    override fun onCreate(db: SQLiteDatabase?) {
        // create the table
        db?.execSQL("""
            CREATE TABLE characters ( 
                uri     TEXT NOT NULL, 
                name    TEXT NOT NULL,
                race    TEXT NOT NULL,
                jobclass    TEXT NOT NULL,
                background    TEXT NOT NULL,
                langandprof TEXT NOT NULL,
                features TEXT NOT NULL,
                bio TEXT NOT NULL,
                statstr INTEGER NOT NULL,
                statdex INTEGER NOT NULL,
                statcon INTEGER NOT NULL,
                statint INTEGER NOT NULL,
                statwis INTEGER NOT NULL
)
        """)

        /*
        db?.execSQL("""
            INSERT INTO characters VALUES
                ("uri", "name", ....... , 17, .....)
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