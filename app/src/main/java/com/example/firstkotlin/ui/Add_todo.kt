package com.example.firstkotlin.ui

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.firstkotlin.R
import com.example.firstkotlin.data.entity.Todo
import com.example.firstkotlin.viewModel.TodoViewModel
import kotlinx.android.synthetic.main.add_todo_fragment.*


class Add_todo : Fragment() {

    private lateinit var text_header: EditText;
    private lateinit var text_desc: EditText;
    private val todoViewModel: TodoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.add_todo_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        text_header = textHeader
        text_desc = textDescription
        addTodoButton.setOnClickListener {
            val todo:Todo = Todo(text_header.text.toString(),text_desc.text.toString())
            context?.let { it1 -> todoViewModel.addTodo(it1,todo) }
            Navigation.findNavController(view).navigate(R.id.action_add_todo_to_listFragment)
            hideKeyboard()

        }

    }


    fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    fun Activity.hideKeyboard() {
        hideKeyboard(currentFocus ?: View(this))
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

}