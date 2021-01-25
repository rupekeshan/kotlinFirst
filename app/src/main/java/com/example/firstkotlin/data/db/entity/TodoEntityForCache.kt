package com.example.firstkotlin.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "todo_first")
data class TodoEntityForCache(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,
    @ColumnInfo(name = "header")
    var header: String,
    @ColumnInfo(name = "body")
    var body: String
) {

}