package com.shem.todoapp160420033.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.shem.todoapp160420033.R
import com.shem.todoapp160420033.databinding.FragmentCreateTodoBinding
import com.shem.todoapp160420033.model.Todo
import com.shem.todoapp160420033.util.NotificationHelper
import com.shem.todoapp160420033.util.TodoWorker
import com.shem.todoapp160420033.viewmodel.DetailTodoViewModel
import java.util.concurrent.TimeUnit


class CreateTodoFragment : Fragment(), TodoCreateLayoutInterface {
    private lateinit var viewModel: DetailTodoViewModel
    private lateinit var dataBinding:FragmentCreateTodoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_todo,container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.todo = Todo("","",3,0)
        viewModel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)
        dataBinding.buttonListener = this
        dataBinding.radioListener = this


/*        val btnAdd = view.findViewById<Button>(R.id.btnAdd)
//        btnAdd.setOnClickListener {
//            val myWorkRequest = OneTimeWorkRequestBuilder<TodoWorker>()
//                .setInitialDelay(30, TimeUnit.SECONDS)
//                .setInputData(
//                    workDataOf(
//                    "title" to "Todo Created",
//                    "message" to "A new Todo has been created! Stay focused!")
//                )
//                .build()
//            WorkManager.getInstance(requireContext()).enqueue(myWorkRequest)
//
//
//            val txtTitle = view.findViewById<EditText>(R.id.txtTitle)
//            val txtNotes = view.findViewById<EditText>(R.id.txtNotes)
//            val radioGroup = view.findViewById<RadioGroup>(R.id.radioGroupPriority)
//            val radioButton = view.findViewById<RadioButton>(radioGroup.checkedRadioButtonId)
//            val todo = Todo(txtTitle.text.toString(), txtNotes.text.toString(), radioButton.tag.toString().toInt())
//            viewModel.addTodo(todo)
//            Toast.makeText(view.context, "Todo created", Toast.LENGTH_LONG).show()
//            Navigation.findNavController(it).popBackStack()
        }*/
    }

    override fun onRadioClick(v: View, priority: Int, obj: Todo) {
        obj.priority = priority
    }

    override fun onButtonAddClick(v: View) {
        viewModel.addTodo(dataBinding.todo!!)
        Toast.makeText(v.context, "Todo Created",Toast.LENGTH_SHORT).show()

        val myWorkRequest = OneTimeWorkRequestBuilder<TodoWorker>()
                .setInitialDelay(30, TimeUnit.SECONDS)
                .setInputData(
                    workDataOf(
                    "title" to "Todo Created",
                    "message" to "A new Todo has been created! Stay focused!")
                )
                .build()
        WorkManager.getInstance(requireContext()).enqueue(myWorkRequest)

        Navigation.findNavController(v).popBackStack()

    }
}