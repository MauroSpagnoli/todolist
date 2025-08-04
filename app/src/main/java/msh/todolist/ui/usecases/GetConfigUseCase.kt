package msh.todolist.ui.usecases

import com.google.gson.Gson
import msh.todolist.constants.Constants.LOG_TAG
import msh.todolist.dto.ConfigDto
import msh.todolist.tools.Log
import java.io.File

class GetConfigUseCase {
    fun execute(filesDir: File): Boolean {
        // Comprobar si existe el fichero de configuración de login
        val configFile = File(filesDir, "login_config.txt")
        if (!configFile.exists()){
            Log.d("Config file not found", LOG_TAG)
            configFile.createNewFile()
            return true
        }
        else {
            try {
                Log.d("Config file found", LOG_TAG)
                val configDto = Gson().fromJson(configFile.readText(), ConfigDto::class.java)
            }
            catch (e: Exception){
                Log.d("Error reading config file", LOG_TAG)
                return true
            }
            return false
        }
    }
}