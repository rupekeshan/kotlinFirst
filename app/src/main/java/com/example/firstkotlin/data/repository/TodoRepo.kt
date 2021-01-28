package com.example.firstkotlin.data.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.util.Log
import com.example.firstkotlin.data.db.dao.TodoDao
import com.example.firstkotlin.data.db.entity.CacheMapper
import com.example.firstkotlin.data.firebase.FirebaseTodoOperation
import com.example.firstkotlin.data.firebase.entity.NetworkEntityMapper
import com.example.firstkotlin.model.Todo
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class TodoRepo @Inject constructor(
    @ApplicationContext val context: Context,
    private val todoDao: TodoDao,
    private val firebaseTodoOperation: FirebaseTodoOperation,
    private val cacheMapper: CacheMapper,
    private val networkEntityMapper: NetworkEntityMapper
) {

    private val TAG = "TodoRepo"
    //cache

    suspend fun insert(todo: Todo): Long {
        val todoEntityForCache = cacheMapper.mapToEntity(todo)
        return todoDao.addData(todoEntityForCache)
    }

    fun getallDetail(): Flow<List<Todo>> {
        return todoDao.getAllDataWithLive().map {
            cacheMapper.mapFromListEntity(it)
        }
    }

    suspend fun deleteData(todo: Todo): Int {
        val todoEntityForCache = cacheMapper.mapToEntity(todo)
        return todoDao.deleteData(todoEntityForCache)
    }

    //network

    suspend fun syncWithCloud() {
        if (isNetworkAvailable()) {
            Log.e(TAG, "syncWithCloud: in sync")
            todoDao.getAllDataWithLive().collect {
                val datafromInternet =
                    networkEntityMapper.mapFromListEntity(firebaseTodoOperation.getAllDetail()!!)
                val diff = cacheMapper.mapFromListEntity(it).filterNot { todo ->
                    datafromInternet.contains(todo)
                }
                firebaseTodoOperation.addListofData(networkEntityMapper.mapToListEntity(diff))
            }
        }
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