package com.example.firstkotlin.data.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.firstkotlin.data.db.RoomDB
import com.example.firstkotlin.data.db.entity.Todo
import com.example.firstkotlin.data.firebase.TodoFireBase
import javax.inject.Inject


class TodoRepo @Inject constructor(val db: RoomDB, val context: Context) {

    private val todoFireBase: TodoFireBase = TodoFireBase()

    suspend fun insert(todo: Todo) {
//        syncWithCloud(db.todoDao().getAllData())
        db.todoDao().addData(todo)
    }

    fun getallDetail(): LiveData<List<Todo>> {
//        syncWithCloud(db.todoDao().getAllData())
        return db.todoDao().getAllDataWithLive()
    }

    private fun syncWithCloud(todoDBList: List<Todo>) {
        if (isNetworkAvailable()) {
            todoFireBase.sync(todoDBList)
        }
    }

    suspend fun deleteData(todo: Todo) {
//        syncWithCloud(db.todoDao().g etAllData())
        db.todoDao().deleteData(todo)
    }

    private fun isNetworkAvailable(): Boolean {
        val cm =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networks: Array<Network> = cm.allNetworks
        var hasInternet = false
        if (networks.isNotEmpty()) {
            for (network in networks) {
                val nc = cm.getNetworkCapabilities(network)
                if (nc!!.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)) hasInternet =
                    true
            }
        }
        Log.d("TAG", "isNetworkAvailable: ${hasInternet}")
        return hasInternet
    }

}