package com.example.firstkotlin.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "todo_first")
data class Todo(

    @ColumnInfo(name = "header")
    var header: String,
    @ColumnInfo(name = "body")
    var body: String
){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id:Int? =null
}