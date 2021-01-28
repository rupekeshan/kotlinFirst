package com.example.firstkotlin.data.db.dao

import androidx.room.*
import com.example.firstkotlin.data.db.entity.TodoEntityForCache
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Query("SELECT * FROM todo_first")
    fun getAllDataWithLive(): Flow<List<TodoEntityForCache>>

    @Query("SELECT * FROM todo_first")
    fun getAllData(): List<TodoEntityForCache>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addData(todoEntityForCache:TodoEntityForCache):Long

    @Delete
    suspend fun deleteData(todoEntityForCache:TodoEntityForCache):Int

}