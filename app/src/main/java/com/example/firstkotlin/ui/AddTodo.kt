package com.example.firstkotlin.ui

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
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
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.example.firstkotlin.R
import com.example.firstkotlin.databinding.AddTodoFragmentBinding
import com.example.firstkotlin.model.Todo
import com.example.firstkotlin.util.DataState
import com.example.firstkotlin.util.Operations
import com.example.firstkotlin.viewModel.TodoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTodo : DialogFragment() {

    private lateinit var textHeader: EditText
    private lateinit var textDesc: EditText
    private val todoViewModel: TodoViewModel by viewModels()

    private lateinit var binding: AddTodoFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_todo_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding = AddTodoFragmentBinding.bind(view)
        binding.showProgress.visibility = View.GONE
        textHeader = binding.textHeader
        textDesc = binding.textDescription
        binding.saveButton.setOnClickListener {
            if (validateInput(textHeader.text.toString(), textDesc.text.toString())) {
                submitData()
            }
            hideKeyboard()
        }

        textDesc.setOnEditorActionListener { v, actionId, event ->
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


    private fun submitData() {
        val todo = Todo(textHeader.text.toString(), textDesc.text.toString())
        val job = todoViewModel.makeDataOperation(todo,Operations.ADD)
        job.observe(viewLifecycleOwner, Observer {

            Log.e("TAG", "submitData: $it")
            when (it) {
                is DataState.Success -> {
                    if (it.data == "close") {
                        binding.showProgress.visibility = View.GONE
                        dialog?.dismiss()
                    }
                }
                is DataState.Loading -> {
                    binding.showProgress.visibility = View.VISIBLE
                }
                else -> {
                    Toast.makeText(context, "Something Worng", Toast.LENGTH_SHORT).show()
                }
            }
        })

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