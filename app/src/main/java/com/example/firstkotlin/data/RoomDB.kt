package com.example.firstkotlin.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.firstkotlin.data.dao.TodoDao
import com.example.firstkotlin.data.entity.Todo

@Database(entities = arrayOf(Todo::class), version = 1)
abstract class RoomDB : RoomDatabase() {


    abstract fun todoDao(): TodoDao

    companion object {

        @Volatile
        private var INSTANCE: RoomDB? = null

        fun getDb(context: Context): RoomDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomDB::class.java,
                    "myDB"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}