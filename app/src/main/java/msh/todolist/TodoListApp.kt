package msh.todolist

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp
import msh.todolist.constants.Constants.LOG_TAG
import javax.inject.Inject

@HiltAndroidApp
class TodoListApp : Application()