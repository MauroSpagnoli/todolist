package msh.todolist

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp
import msh.todolist.constants.Constants.LOG_TAG
import msh.todolist.initialization.InitializationManager
import javax.inject.Inject

@HiltAndroidApp
class TodoListApp : Application() {

    @Inject
    lateinit var initializationManager: InitializationManager

    override fun onCreate() {
        super.onCreate()
        Log.d(LOG_TAG, "TodoListApp onCreate - Initialization started")
        initializationManager.initialize()
    }
}