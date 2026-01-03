package msh.todolist.domain.repository

import msh.todolist.dto.ConfigDto

interface IConfigRepository {
    fun getConfigFile(): ConfigDto?
}