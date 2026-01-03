package msh.todolist.domain.usecases.config

import msh.todolist.domain.repository.ConfigRepository
import msh.todolist.dto.ConfigDto
import javax.inject.Inject

class GetConfigUseCase @Inject constructor(val configRepository: ConfigRepository) {

    operator fun invoke(): ConfigDto? {
        return configRepository.getConfigFile()
    }
}