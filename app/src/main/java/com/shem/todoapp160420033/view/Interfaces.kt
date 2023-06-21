package com.shem.todoapp160420033.view

import android.view.View
import android.widget.CompoundButton
import com.shem.todoapp160420033.model.Todo

interface TodoItemLayoutInterface{
    fun onCheckedChange(cb:CompoundButton,
                        isChecked:Boolean,
                        obj: Todo)
    fun onTodoEditClick(v: View)
}

interface RadioClick{
    fun onRadioClick(v:View, priority:Int, obj:Todo)
}

interface TodoSaveChangesClick{
    fun onTodoSaveChangesClick(v: View, obj: Todo)
}