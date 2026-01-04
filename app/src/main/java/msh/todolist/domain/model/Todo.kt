package msh.todolist.domain.model

data class Todo(
    val id: Long = 0L,
    val title: String,
    val description: String? = null,
    val completed: Boolean = false
)

