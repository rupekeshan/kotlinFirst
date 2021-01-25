package com.example.firstkotlin.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.firstkotlin.data.db.entity.TodoEntityForCache

@Dao
interface TodoDao {

    @Query("SELECT * FROM todo_first")
    fun getAllDataWithLive(): LiveData<List<TodoEntityForCache>>

    @Query("SELECT * FROM todo_first")
    fun getAllData(): List<TodoEntityForCache>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addData(todoEntityForCache:TodoEntityForCache)

    @Delete
    suspend fun deleteData(todoEntityForCache:TodoEntityForCache)

}