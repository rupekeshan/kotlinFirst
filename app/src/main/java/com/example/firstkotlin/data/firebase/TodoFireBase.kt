package com.example.firstkotlin.data.firebase

import android.util.Log
import com.example.firstkotlin.data.db.entity.Todo
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase

class TodoFireBase {
    private val TAG = "TodoFireBase"


    val fire = Firebase.firestore
    lateinit var fireList: MutableList<Todo>

    private fun getAllDetail(): List<Todo>? {
        var dat: List<Todo>? = null
        fire.collection("todoAndroid").get()
            .addOnSuccessListener {
                Log.e(TAG, "getAllDetail: ${it.documents[0].data}")
                dat = it.toObjects<Todo>()
                Log.e(TAG, "getAllDetail: $dat")

            }
            .addOnFailureListener {
                Log.e(TAG, "getAllDetail:", it)
            }

        return dat
    }

    fun sync(todoDBList: List<Todo>) {
        if (todoDBList == getAllDetail()) {
            Log.e(TAG, "sync: working")
        }
        todoDBList.forEach {
            addData(it)
        }

    }

    private fun addData(todo: Todo) {
        fire.collection("todoAndroid")
            .add(todo)
            .addOnCompleteListener {
                Log.e(TAG, "addData: success")
            }
            .addOnFailureListener {
                Log.e(TAG, "addData: Failed", it)
            }
    }


}