package com.example.firstkotlin.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.firstkotlin.data.db.dao.TodoDao
import com.example.firstkotlin.data.db.entity.Todo

@Database(entities = [Todo::class], version = 1)
abstract class RoomDB : RoomDatabase() {


    abstract fun todoDao(): TodoDao

    companion object {

        @Volatile
        private var INSTANCE: RoomDB? = null

        operator fun invoke(context: Context)=
            INSTANCE ?: synchronized(this) {
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