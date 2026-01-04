package msh.todolist.data.local

import msh.todolist.domain.model.Todo

fun TodoEntity.toDomain(): Todo = Todo(
    id = this.id,
    title = this.title,
    description = this.description,
    completed = this.completed
)

fun Todo.toEntity(): TodoEntity = TodoEntity(
    id = this.id,
    title = this.title,
    description = this.description,
    completed = this.completed
)

