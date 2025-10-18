package msh.todolist.data

import android.content.Context
import com.google.gson.Gson
import msh.todolist.R
import msh.todolist.constants.Constants.LOG_TAG
import msh.todolist.dto.ConfigDto
import msh.todolist.tools.Log
import java.io.InputStreamReader

class ConfigDataSource(private val context: Context) {
    fun getConfigFile(): ConfigDto? {
        try {
            Log.d("Reading config file", LOG_TAG)
            // Acceder al recurso raw
            val inputStream = context.resources.openRawResource(R.raw.config_json)
            val reader = InputStreamReader(inputStream)

            // Parsear el JSON usando Gson
            val configDto = Gson().fromJson(reader, ConfigDto::class.java)
            reader.close()
            Log.d("Config file read successfully", LOG_TAG)
            return configDto
        } catch (e: Exception) {
            Log.d("Error reading config file: ${e.message}", LOG_TAG)
            return null
        }
    }
}