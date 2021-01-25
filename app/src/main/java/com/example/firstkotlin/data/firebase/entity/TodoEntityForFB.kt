package com.example.firstkotlin.data.firebase.entity


data class TodoEntityForFB(
    var id: Int,
    var header: String,
    var body: String
) {

    constructor() : this(id = 0, header = "", body = "")

}