package com.example.firstkotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.firstkotlin.MainActivity
import com.example.firstkotlin.R
import com.example.firstkotlin.data.entity.Todo
import com.example.firstkotlin.viewModel.TodoViewModel
import kotlinx.android.synthetic.main.recycle_list_holder.view.*

class TodoRecycleAdapter : RecyclerView.Adapter<TodoRecycleAdapter.TodoRecycleHolder>() {

    private var my: List<Todo> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoRecycleHolder {
        return TodoRecycleHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recycle_list_holder, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TodoRecycleHolder, position: Int) {
        holder.bind(my[position])
        holder.itemView.setOnClickListener {
            val id = my[position].id?.toInt()
            val bundle = bundleOf("id" to id)
            Navigation.findNavController(holder.itemView)
                .navigate(R.id.action_listFragment_to_detailed_todo, bundle);
        }


    }

    override fun getItemCount(): Int {
        return my.size
    }

    fun initializeList(list: List<Todo>) {
        my = list
        notifyDataSetChanged()
    }

    class TodoRecycleHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var headerText: TextView = itemView.header_todo
        private var descText: TextView = itemView.des_todo


        fun bind(list: Todo) {
            headerText.text = list.header
            descText.text = list.body

            itemView.deleteList.setOnClickListener {
                val id = list.id
                Toast.makeText(it.context,"$id",Toast.LENGTH_LONG).show()
            }
        }


    }
}