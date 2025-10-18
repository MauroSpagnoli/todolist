package msh.todolist.domain

import msh.todolist.dto.ConfigDto

interface IConfigRepository {
    fun getConfigFile(): ConfigDto?
}