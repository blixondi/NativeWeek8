package com.shem.todoapp160420033.util

import android.content.Context
import androidx.room.Room
import com.shem.todoapp160420033.model.TodoDatabase

val DB_NAME = "tododb"

fun buildDb(context: Context):TodoDatabase{
    val db = Room.databaseBuilder(context, TodoDatabase::class.java, "tododb").build()
    return db
}