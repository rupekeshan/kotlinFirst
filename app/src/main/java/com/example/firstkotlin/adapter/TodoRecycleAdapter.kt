package com.example.firstkotlin.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.firstkotlin.R
import com.example.firstkotlin.databinding.RecycleListHolderBinding
import com.example.firstkotlin.model.Todo
import com.example.firstkotlin.util.DataState
import com.example.firstkotlin.util.Operations
import com.example.firstkotlin.viewModel.TodoViewModel


class TodoRecycleAdapter(
    private val todoViewModel: TodoViewModel,
    val viewLifecycleOwner: LifecycleOwner
) :
    RecyclerView.Adapter<TodoRecycleAdapter.TodoRecycleHolder>() {

    private var my: List<Todo> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoRecycleHolder {
        return TodoRecycleHolder(
            RecycleListHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: TodoRecycleHolder, position: Int) {
        holder.bind(my[position])
        holder.itemView.setOnClickListener {
            val id = my[position].id
            val bundle = bundleOf("id" to id)
            Navigation.findNavController(holder.itemView)
                .navigate(R.id.action_listFragment_to_detailed_todo, bundle);
        }
        holder.binding.deleteList.setOnClickListener {
            val todo = my[position]
            todoViewModel.makeDataOperation(todo, Operations.DELETE).observe(viewLifecycleOwner,
                Observer {
                    Log.e("TAG", "submitData: $it")
                    when (it) {
                        is DataState.Success -> {
                            if (it.data == "close") {
                                holder.binding.progressBarForList.visibility = View.GONE
                            }
                        }
                        is DataState.Loading -> {
                            holder.binding.progressBarForList.visibility = View.VISIBLE
                        }
                        else -> {
                        }
                    }
                })

        }

    }

    override fun getItemCount(): Int {
        return my.size
    }

    fun initializeList(list: List<Todo>) {
        my = list
        notifyDataSetChanged()
    }

    class TodoRecycleHolder(val binding: RecycleListHolderBinding) :
        RecyclerView.ViewHolder(binding.root) {


        private var headerText: TextView = binding.headerTodo
        private var descText: TextView = binding.desTodo


        fun bind(list: Todo) {
            headerText.text = list.header
            descText.text = list.body

        }


    }
}