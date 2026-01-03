package msh.todolist.data.datasource

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import msh.todolist.R
import msh.todolist.constants.Constants
import msh.todolist.dto.ConfigDto
import java.io.InputStreamReader
import javax.inject.Inject

class ConfigDataSource @Inject constructor(private val context: Context) {
    fun getConfigFile(): ConfigDto? {
        try {
            Log.d(Constants.LOG_TAG, "Reading config file")
            // Acceder al recurso raw
            val inputStream = context.resources.openRawResource(R.raw.config_json)
            val reader = InputStreamReader(inputStream)

            // Parsear el JSON usando Gson
            val configDto = Gson().fromJson(reader, ConfigDto::class.java)
            reader.close()
            Log.d(Constants.LOG_TAG, "Config file read successfully")
            return configDto
        } catch (e: Exception) {
            Log.d(Constants.LOG_TAG, "Error reading config file: ${e.message}")
            return null
        }
    }
}