package com.shem.todoapp160420033.view

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
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
import java.util.*
import java.util.concurrent.TimeUnit


class CreateTodoFragment : Fragment(), TodoCreateLayoutInterface, DateClickListener, TimeClickListener,
    DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    private lateinit var viewModel: DetailTodoViewModel
    private lateinit var dataBinding:FragmentCreateTodoBinding

    var year = 0
    var month = 0
    var day = 0
    var hour = 0
    var minute = 0
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
        dataBinding.timeListener = this
        dataBinding.dateListener = this


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
        val c = Calendar.getInstance()
        c.set(year,month,day,hour,minute,0)

        val today = Calendar.getInstance()
        val diff = (c.timeInMillis/1000L) - (today.timeInMillis/1000L)

        dataBinding.todo!!.todo_date = (c.timeInMillis/1000L).toInt()

        viewModel.addTodo(dataBinding.todo!!)
        Toast.makeText(v.context, "Todo Created",Toast.LENGTH_SHORT).show()

        val myWorkRequest = OneTimeWorkRequestBuilder<TodoWorker>()
                .setInitialDelay(diff, TimeUnit.SECONDS)
                .setInputData(
                    workDataOf(
                    "title" to "Todo Created",
                    "message" to "A new Todo has been created! Stay focused!")
                )
                .build()
        WorkManager.getInstance(requireContext()).enqueue(myWorkRequest)

        Navigation.findNavController(v).popBackStack()

    }

    override fun onDateClick(v: View) {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        activity?.let{
            it -> DatePickerDialog(it, this, year,month,day).show()
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, day: Int) {
        val c = Calendar.getInstance()
        c.set(year, month, day)
        dataBinding.txtDate.setText(day.toString().padStart(2,'0') +
                                    "-" + month.toString().padStart(2,'0') +
                                    "-" + year)
        this.year = year
        this.month = month
        this.day = day
    }

    override fun onTimeClick(v: View) {
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)
        activity?.let{
            TimePickerDialog(activity, this, hour ,minute, DateFormat.is24HourFormat(activity)).show()
        }
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        dataBinding.txtTime.setText(
            hourOfDay.toString().padStart(2,'0') + ":" +
                    minute.toString().padStart(2,'0')
        )
        this.hour = hourOfDay
        this.minute = minute
    }


}