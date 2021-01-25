package com.example.firstkotlin.di

import com.example.firstkotlin.data.firebase.FirebaseTodoOperation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object FBModule {


    @Singleton
    @Provides
    fun provideFB(): FirebaseTodoOperation {
        return FirebaseTodoOperation()
    }


}