package msh.todolist.domain.repository

import msh.todolist.data.datasource.ConfigDataSource
import msh.todolist.dto.ConfigDto
import javax.inject.Inject

class ConfigRepository @Inject constructor(private val configDataSource: ConfigDataSource) : IConfigRepository {
    override fun getConfigFile(): ConfigDto? {
        return configDataSource.getConfigFile()
    }
}