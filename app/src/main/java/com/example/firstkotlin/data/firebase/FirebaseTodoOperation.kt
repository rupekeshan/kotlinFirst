package com.example.firstkotlin.data.firebase

import android.util.Log
import com.example.firstkotlin.data.firebase.entity.TodoEntityForFB
import com.example.firstkotlin.util.Constant.COLLECTION_NAME
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class FirebaseTodoOperation {
    private val TAG = "TodoFireBase"


    private val fire = Firebase.firestore
    private val collection = fire.collection(COLLECTION_NAME)
//    lateinit var fireList: MutableList<TodoEntityForFB>

    suspend fun getAllDetail(): List<TodoEntityForFB>? {
        Log.e(TAG, "getAllDetail: Started" )

        return try {
            Log.e(TAG, "getAllDetail: start fetching data" )
            collection.get().await().toObjects(TodoEntityForFB::class.java)
        }
        catch (e:Exception){
            Log.e(TAG, "getAllDetail: sorry",e )
            null
        }

    }


    private fun addData(todoEntityForFB: TodoEntityForFB) {
        collection.add(todoEntityForFB)
            .addOnCompleteListener {
                Log.e(TAG, "addData: success")
            }
            .addOnFailureListener {
                Log.e(TAG, "addData: Failed", it)
            }
    }

    fun addListofData(todoEntityForFBList: List<TodoEntityForFB>) {
        todoEntityForFBList.forEach {
            addData(it)
        }
    }


}