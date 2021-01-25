package com.example.firstkotlin.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.firstkotlin.data.db.dao.TodoDao
import com.example.firstkotlin.data.db.entity.TodoEntityForCache

@Database(entities = [TodoEntityForCache::class], version = 3)
abstract class TodoDB : RoomDatabase() {

    abstract fun todoDao(): TodoDao

}