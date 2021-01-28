package com.example.firstkotlin.data.firebase.entity

import com.example.firstkotlin.model.Todo
import com.example.firstkotlin.util.EntityMapper
import javax.inject.Inject

class NetworkEntityMapper @Inject constructor() : EntityMapper<TodoEntityForFB, Todo> {
    override fun mapFromEntity(entity: TodoEntityForFB): Todo {
        return Todo(
            id = entity.id,
            header = entity.header,
            body = entity.body
        )
    }

    override fun mapToEntity(domainModel: Todo): TodoEntityForFB {
        return TodoEntityForFB(
            id = domainModel.id,
            header = domainModel.header,
            body = domainModel.body
        )

    }

    fun mapFromListEntity(entities: List<TodoEntityForFB>): List<Todo> {
        return entities.map { mapFromEntity(it) }
    }

    fun mapToListEntity(enitites: List<Todo>): List<TodoEntityForFB> {
        return enitites.map {
            mapToEntity(it)
        }
    }

}