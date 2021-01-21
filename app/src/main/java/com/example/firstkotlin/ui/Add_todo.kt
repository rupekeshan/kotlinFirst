package com.example.firstkotlin.ui

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.firstkotlin.R
import com.example.firstkotlin.data.db.RoomDB
import com.example.firstkotlin.data.db.entity.Todo
import com.example.firstkotlin.data.repository.TodoRepo
import com.example.firstkotlin.viewModel.TodoViewModel
import com.example.firstkotlin.viewModel.vmfactory.TodoVMFactory
import kotlinx.android.synthetic.main.add_todo_fragment.*


class Add_todo : DialogFragment() {

    private lateinit var text_header: EditText;
    private lateinit var text_desc: EditText;
    private lateinit var todoViewModel: TodoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_todo_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        todoViewModel = ViewModelProvider(
            this,
            TodoVMFactory(TodoRepo(
                RoomDB(
                    requireContext()
                ),requireContext()
            ))
        ).get(TodoViewModel::class.java)

        text_header = textHeader
        text_desc = textDescription
        saveButton.setOnClickListener {
            if (validateInput(text_header.text.toString(), text_desc.text.toString())) {
                submitData()
            }
            hideKeyboard()
        }

        text_desc.setOnEditorActionListener { v, actionId, event ->
            val handle: Boolean = false
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                submitData()
            }
            return@setOnEditorActionListener handle
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                view?.let { Navigation.findNavController(it).navigateUp() }
            }
        })
    }

    private fun validateInput(textHeader: String, textDesc: String): Boolean {
        if (textHeader.isEmpty() || textDesc.isEmpty()) {
            Toast.makeText(context, "ALl fields are mandatory", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }


    fun submitData() {
        val todo = Todo(text_header.text.toString(), text_desc.text.toString())
        context?.let { it1 -> todoViewModel.addTodo(todo) }
        dialog?.dismiss()
    }

    fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    fun Activity.hideKeyboard() {
        hideKeyboard(currentFocus ?: View(this))
    }

    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

}