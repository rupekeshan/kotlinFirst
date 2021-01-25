package com.example.firstkotlin.di

import android.content.Context
import androidx.room.Room
import com.example.firstkotlin.data.db.TodoDB
import com.example.firstkotlin.data.db.dao.TodoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DBModule {

    @Singleton
    @Provides
    fun provideDb(@ApplicationContext context: Context): TodoDB {
        return Room.databaseBuilder(
            context.applicationContext,
            TodoDB::class.java,
            "myDB"
        )
            .fallbackToDestructiveMigration()
            .build()

    }

    @Singleton
    @Provides
    fun provideDao(todoDB: TodoDB): TodoDao {
        return todoDB.todoDao()
    }

}