package msh.todolist.data.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val preferences: SharedPreferences = context.getSharedPreferences(
        "app_preferences",
        Context.MODE_PRIVATE
    )

    private val _currentLanguage = MutableStateFlow(getCurrentLanguage())
    val currentLanguage: StateFlow<String> = _currentLanguage.asStateFlow()

    companion object {
        private const val KEY_LANGUAGE = "language"
        const val LANGUAGE_SPANISH = "es"
        const val LANGUAGE_ENGLISH = "en"
    }

    fun getCurrentLanguage(): String {
        return preferences.getString(KEY_LANGUAGE, getSystemLanguage())
            ?: getSystemLanguage()
    }

    fun setLanguage(language: String) {
        preferences.edit {
            putString(KEY_LANGUAGE, language)
        }
        _currentLanguage.value = language
    }

    private fun getSystemLanguage(): String {
        val systemLanguage = Locale.getDefault().language
        return when (systemLanguage) {
            "es" -> LANGUAGE_SPANISH
            "en" -> LANGUAGE_ENGLISH
            else -> LANGUAGE_SPANISH // Default to Spanish
        }
    }

    fun getAvailableLanguages(): List<Pair<String, String>> {
        return listOf(
            LANGUAGE_SPANISH to "Español",
            LANGUAGE_ENGLISH to "English"
        )
    }
}
