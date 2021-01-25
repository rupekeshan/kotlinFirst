package com.example.firstkotlin.model


data class Todo(
    var id: Int,
    var header: String,
    var body: String
) {
    constructor(header: String, body: String) : this(id = 0, header = header, body = body)
}