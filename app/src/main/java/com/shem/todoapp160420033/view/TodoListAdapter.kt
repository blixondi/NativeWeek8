package com.shem.todoapp160420033.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.shem.todoapp160420033.R
import com.shem.todoapp160420033.databinding.TodoItemLayoutBinding
import com.shem.todoapp160420033.model.Todo

class TodoListAdapter(val todoList:ArrayList<Todo>,
                      val adapterOnClick : (Todo) -> Unit) : RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>() {
    class TodoViewHolder(var view:TodoItemLayoutBinding): RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = TodoItemLayoutBinding.inflate(inflater, parent, false)
        return TodoViewHolder(view)
    }
    override fun onBindViewHolder(holder: TodoViewHolder, position: Int)
    {
        holder.view.todo = todoList[position]
        /*var checktask = holder.view.findViewById<CheckBox>(R.id.checkTask)
        var imgEdit = holder.view.findViewById<ImageView>(R.id.imgEdit)
        checktask.text = todoList[position].title
        checktask.setOnCheckedChangeListener { compoundButton, isChecked ->
            if(isChecked == true){
                adapterOnClick(todoList[position])
            }
        }
        imgEdit.setOnClickListener {
            val action = TodoListFragmentDirections.actionEditTodoFragment(todoList[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }*/
    }
    override fun getItemCount(): Int {
        return todoList.size
    }

    fun updateTodoList(newTodoList: List<Todo>) {
        todoList.clear()
        todoList.addAll(newTodoList)
        notifyDataSetChanged()
    }

}
