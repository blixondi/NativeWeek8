package com.shem.todoapp160420033.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.shem.todoapp160420033.R
import com.shem.todoapp160420033.databinding.FragmentEditTodoBinding
import com.shem.todoapp160420033.model.Todo
import com.shem.todoapp160420033.viewmodel.DetailTodoViewModel


class EditTodoFragment : Fragment(), RadioClick, TodoCreateLayoutInterface, DateClickListener, TimeClickListener {
    private lateinit var viewModel: DetailTodoViewModel
    private lateinit var dataBinding:FragmentEditTodoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit_todo, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)
        val uuid = EditTodoFragmentArgs.fromBundle(requireArguments()).uuid
        viewModel.fetch(uuid)
        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.todoLD.observe(viewLifecycleOwner, Observer {
            dataBinding.todo = it
            dataBinding.radioListener = this
            dataBinding.buttonListener = this
            dataBinding.dateListener = this
            dataBinding.timeListener = this

            /*val txtTitle = view?.findViewById<EditText>(R.id.txtTitle)
            val txtNotes = view?.findViewById<EditText>(R.id.txtNotes)
            val radioLow = view?.findViewById<RadioButton>(R.id.radioLow)
            val radioMedium = view?.findViewById<RadioButton>(R.id.radioMedium)
            val radioHigh = view?.findViewById<RadioButton>(R.id.radioHigh)
            txtTitle?.setText(it.title)
            txtNotes?.setText(it.notes)
            when(it.priority){
                1 -> radioLow?.isChecked = true
                2 -> radioMedium?.isChecked = true
                else -> radioHigh?.isChecked = true
            }*/
        })
    }

    override fun onRadioClick(v: View, priority: Int, obj: Todo) {
        obj.priority = priority
    }

    override fun onButtonAddClick(v: View) {
        dataBinding.todo?.let{
            viewModel.update(it.title, it.notes, it.priority, it.uuid)
            Toast.makeText(v.context, "Todo Updated", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(v).popBackStack()
        }
    }

    override fun onDateClick(v: View) {
        TODO("Not yet implemented")
    }

    override fun onTimeClick(v: View) {
        TODO("Not yet implemented")
    }

}