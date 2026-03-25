package msh.todolist.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import msh.todolist.data.preferences.PreferencesManager
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val preferencesManager: PreferencesManager
) : ViewModel() {

    val currentLanguage: StateFlow<String> = preferencesManager.currentLanguage

    fun setLanguage(languageCode: String) {
        viewModelScope.launch {
            preferencesManager.setLanguage(languageCode)
        }
    }

    fun getAvailableLanguages(): List<Pair<String, String>> {
        return preferencesManager.getAvailableLanguages()
    }
}
