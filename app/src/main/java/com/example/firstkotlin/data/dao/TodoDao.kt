package com.example.firstkotlin.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.firstkotlin.data.entity.Todo
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Query("SELECT * FROM todo_first")
    fun getAllData(): Flow<List<Todo>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addData(todo:Todo)

}