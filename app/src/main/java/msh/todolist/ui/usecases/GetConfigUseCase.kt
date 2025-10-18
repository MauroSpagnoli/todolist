package msh.todolist.ui.usecases

import msh.todolist.domain.ConfigRepository
import msh.todolist.dto.ConfigDto
import javax.inject.Inject

class GetConfigUseCase @Inject constructor(val configRepository: ConfigRepository) {

    operator fun invoke(): ConfigDto? {
        return configRepository.getConfigFile()
    }
}