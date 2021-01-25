package com.example.firstkotlin.data.db.entity

import com.example.firstkotlin.model.Todo
import com.example.firstkotlin.util.EntityMapper
import javax.inject.Inject

class CacheMapper @Inject constructor() : EntityMapper<TodoEntityForCache, Todo> {
    override fun mapFromEntity(entity: TodoEntityForCache): Todo {
        return Todo(
            id = entity.id,
            header = entity.header,
            body = entity.body
        )
    }

    override fun mapToEntity(domainModel: Todo): TodoEntityForCache {

        return TodoEntityForCache(
            id = domainModel.id,
            header = domainModel.header,
            body = domainModel.body
        )

    }

    fun mapFromListEntity(entities:List<TodoEntityForCache>): List<Todo> {
        return entities.map { mapFromEntity(it) }
    }
}