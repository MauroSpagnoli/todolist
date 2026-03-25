package msh.todolist

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp
import msh.todolist.data.preferences.PreferencesManager
import msh.todolist.utils.LanguageHelper
import javax.inject.Inject

@HiltAndroidApp
class TodoListApp : Application() {

    @Inject
    lateinit var preferencesManager: PreferencesManager

    override fun onCreate() {
        super.onCreate()
        // Apply saved language on app start
        val savedLanguage = preferencesManager.getCurrentLanguage()
        LanguageHelper.updateLocale(this, savedLanguage)
    }

    override fun attachBaseContext(base: Context) {
        // This is called before onCreate, so we need to read preferences directly
        val prefs = base.getSharedPreferences("app_preferences", MODE_PRIVATE)
        val language = prefs.getString("language", "es") ?: "es"
        val context = LanguageHelper.setLocale(base, language)
        super.attachBaseContext(context)
    }
}
