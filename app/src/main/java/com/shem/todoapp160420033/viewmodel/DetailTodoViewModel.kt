package com.shem.todoapp160420033.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.room.Room
import com.shem.todoapp160420033.model.Todo
import com.shem.todoapp160420033.model.TodoDatabase
import com.shem.todoapp160420033.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailTodoViewModel(application: Application): AndroidViewModel(application), CoroutineScope {
    private val job = Job()

    fun addTodo(todo:Todo) {
        launch {
            val db = buildDb(getApplication())
            db.todoDao().insertAll(todo)
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

}