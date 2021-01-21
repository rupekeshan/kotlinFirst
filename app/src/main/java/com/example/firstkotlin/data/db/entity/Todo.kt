package com.example.firstkotlin.data.db.entity

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
    constructor(id:Int,header: String,body: String):this(header,body)
    constructor():this(id = 0, header = "", body = "")

}