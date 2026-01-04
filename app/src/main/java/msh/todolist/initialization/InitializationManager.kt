package msh.todolist.initialization

import javax.inject.Inject

class InitializationManager @Inject constructor(val getConfigUseCase: GetConfigUseCase) {

    private var _currentUserConfig: ConfigDto? = null

    fun initialize() {
        _currentUserConfig = getConfigUseCase.invoke()
    }
}