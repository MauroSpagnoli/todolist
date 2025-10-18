package msh.todolist.initialization

import msh.todolist.dto.ConfigDto
import msh.todolist.ui.usecases.GetConfigUseCase
import javax.inject.Inject

class InitializationManager @Inject constructor(val getConfigUseCase: GetConfigUseCase) {

    private var _currentUserConfig: ConfigDto? = null

    fun initialize() {
        _currentUserConfig = getConfigUseCase.invoke()
    }
}