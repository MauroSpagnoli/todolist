package msh.todolist

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import msh.todolist.initialization.InitializationManager
import javax.inject.Inject

@HiltAndroidApp
class TodoListApp : Application() {

    @Inject
    lateinit var initializationManager: InitializationManager

    override fun onCreate() {
        super.onCreate()
        initializationManager.initialize()
    }
}