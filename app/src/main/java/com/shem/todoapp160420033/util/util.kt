package com.shem.todoapp160420033.util

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.shem.todoapp160420033.model.TodoDatabase

val DB_NAME = "tododb"

fun buildDb(context: Context):TodoDatabase{
    val db = Room.databaseBuilder(context, TodoDatabase::class.java, "tododb")
        .addMigrations(MIGRATION_1_2)
        .build()
    return db
}

val MIGRATION_1_2 = object : Migration(1,2){
    override fun migrate(database: SupportSQLiteDatabase){
        database.execSQL("ALTER TABLE todo ADD COLUMN priority INTEGER DEFAULT 3 NOT NULL")
        database.execSQL("INSERT INTO todo(title,notes,priority) VALUES('Study hard','Play harder',3)")
    }
}

val MIGRATION_2_3 = object : Migration(2,3){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE todo ADD COLUMN is_done INTEGER DEFAULT 0 NOT NULL")
    }
}