package com.example.firstkotlin.data.firebase

import android.util.Log
import com.example.firstkotlin.data.db.entity.TodoEntityForCache
import com.example.firstkotlin.util.Constant.COLLECTION_NAME
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase

class FirebaseTodoOperation {
    private val TAG = "TodoFireBase"


    val fire = Firebase.firestore
    val collection = fire.collection(COLLECTION_NAME)
    lateinit var fireList: MutableList<TodoEntityForCache>

    private fun getAllDetail(): List<TodoEntityForCache>? {
        var dat: List<TodoEntityForCache>? = null
        collection.get()
            .addOnSuccessListener {
                Log.e(TAG, "getAllDetail: ${it.documents[0].data}")
                dat = it.toObjects<TodoEntityForCache>()
                Log.e(TAG, "getAllDetail: $dat")

            }
            .addOnFailureListener {
                Log.e(TAG, "getAllDetail:", it)
            }

        return dat
    }

    fun sync(todoEntityForDBDBList: List<TodoEntityForCache>) {
        if (todoEntityForDBDBList == getAllDetail()) {
            Log.e(TAG, "sync: working")
        }
        todoEntityForDBDBList.forEach {
            addData(it)
        }

    }

    private fun addData(todoEntityForCache: TodoEntityForCache) {
        collection.add(todoEntityForCache)
            .addOnCompleteListener {
                Log.e(TAG, "addData: success")
            }
            .addOnFailureListener {
                Log.e(TAG, "addData: Failed", it)
            }
    }


}