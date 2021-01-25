package com.example.firstkotlin.data.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.util.Log
import com.example.firstkotlin.data.db.TodoDB
import com.example.firstkotlin.data.db.entity.CacheMapper
import com.example.firstkotlin.data.db.entity.TodoEntityForCache
import com.example.firstkotlin.data.firebase.FirebaseTodoOperation
import com.example.firstkotlin.data.firebase.entity.NetworkEntityMapper
import com.example.firstkotlin.model.Todo
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class TodoRepo @Inject constructor(
    @ApplicationContext val context: Context,
    val db: TodoDB,
    private val firebaseTodoOperation: FirebaseTodoOperation,
    val cacheMapper: CacheMapper,
    val networkEntityMapper: NetworkEntityMapper
) {

    suspend fun insert(todo: Todo) {
//        syncWithCloud(db.todoDao().getAllData())

        val todoEntityForCache = cacheMapper.mapToEntity(todo)
        db.todoDao().addData(todoEntityForCache)
    }

    fun getallDetail(): List<Todo> {
//        syncWithCloud(db.todoDao().getAllData())
        return cacheMapper.mapFromListEntity(db.todoDao().getAllData())
    }

    private fun syncWithCloud(todoEntityForDBDBList: List<TodoEntityForCache>) {
        if (isNetworkAvailable()) {
            firebaseTodoOperation.sync(todoEntityForDBDBList)
        }
    }

    suspend fun deleteData(todo: Todo) {
//        syncWithCloud(db.todoDao().g etAllData())
        val todoEntityForCache = cacheMapper.mapToEntity(todo)
        db.todoDao().deleteData(todoEntityForCache)
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
        Log.d("TAG", "isNetworkAvailable: $hasInternet")
        return hasInternet
    }

}