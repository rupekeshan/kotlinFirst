package com.example.firstkotlin.data.repository

import android.content.Context
import com.example.firstkotlin.data.db.dao.TodoDao
import com.example.firstkotlin.data.db.entity.CacheMapper
import com.example.firstkotlin.model.Todo
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class TodoRepo @Inject constructor(
    @ApplicationContext val context: Context,
    private val todoDao: TodoDao,
    private val cacheMapper: CacheMapper
) {

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



}