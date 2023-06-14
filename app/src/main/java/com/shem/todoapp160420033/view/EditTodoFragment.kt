package com.shem.todoapp160420033.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.shem.todoapp160420033.R
import com.shem.todoapp160420033.databinding.FragmentEditTodoBinding
import com.shem.todoapp160420033.viewmodel.DetailTodoViewModel


class EditTodoFragment : Fragment() {
    private lateinit var viewModel: DetailTodoViewModel
    private lateinit var dataBinding:FragmentEditTodoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_todo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)
        val txtJudulTodo = view.findViewById<TextView>(R.id.txtJudulTodo)
        val btnAdd = view.findViewById<Button>(R.id.btnAdd)
        val txtTitle = view.findViewById<EditText>(R.id.txtTitle)
        val txtNotes = view.findViewById<EditText>(R.id.txtNotes)
        val radioGroup = view.findViewById<RadioGroup>(R.id.radioGroupPriority)
        val radioButton = view.findViewById<RadioButton>(radioGroup.checkedRadioButtonId)
        txtJudulTodo.text = "Edit Todo"
        btnAdd.text = "Save Changes"

        val uuid = EditTodoFragmentArgs.fromBundle(requireArguments()).uuid
        viewModel.fetch(uuid)

        btnAdd.setOnClickListener {
            viewModel.update(txtTitle.text.toString(), txtNotes.text.toString(), radioButton.tag.toString().toInt(), uuid)
            Toast.makeText(view.context, "Todo updated", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(it).popBackStack()
        }
        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.todoLD.observe(viewLifecycleOwner, Observer {
//            val txtTitle = view?.findViewById<EditText>(R.id.txtTitle)
//            val txtNotes = view?.findViewById<EditText>(R.id.txtNotes)
//            val radioLow = view?.findViewById<RadioButton>(R.id.radioLow)
//            val radioMedium = view?.findViewById<RadioButton>(R.id.radioMedium)
//            val radioHigh = view?.findViewById<RadioButton>(R.id.radioHigh)
//            txtTitle?.setText(it.title)
//            txtNotes?.setText(it.notes)
//            when(it.priority){
//                1 -> radioLow?.isChecked = true
//                2 -> radioMedium?.isChecked = true
//                else -> radioHigh?.isChecked = true
//            }
            dataBinding.todo = it
        })
    }

}