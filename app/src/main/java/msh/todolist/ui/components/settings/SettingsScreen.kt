package msh.todolist.ui.components.settings

import androidx.activity.ComponentActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import msh.todolist.R
import msh.todolist.ui.components.common.Layout
import msh.todolist.ui.viewmodel.SettingsViewModel
import msh.todolist.utils.LanguageHelper

@Composable
fun SettingsScreen(
    onTaskSelected: () -> Unit,
    onSettingsSelected: () -> Unit,
    settingsViewModel: SettingsViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val currentLanguage by settingsViewModel.currentLanguage.collectAsState()
    val availableLanguages = settingsViewModel.getAvailableLanguages()

    Layout(
        title = stringResource(R.string.ajustes),
        onTaskSelected = onTaskSelected,
        onSettingsSelected = onSettingsSelected,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Language Setting
            LanguageSettingItem(
                currentLanguage = currentLanguage,
                availableLanguages = availableLanguages,
                onLanguageSelected = { languageCode ->
                    settingsViewModel.setLanguage(languageCode)
                    LanguageHelper.updateLocale(context, languageCode)

                    // Recreate activity to apply language changes
                    (context as? ComponentActivity)?.recreate()
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LanguageSettingItem(
    currentLanguage: String,
    availableLanguages: List<Pair<String, String>>,
    onLanguageSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val currentLanguageName = availableLanguages.find { it.first == currentLanguage }?.second
        ?: availableLanguages.first().second

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(R.string.idioma),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(8.dp))

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                OutlinedTextField(
                    value = currentLanguageName,
                    onValueChange = { },
                    readOnly = true,
                    label = { Text(stringResource(R.string.seleccionar_idioma)) },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = null
                        )
                    },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                        .clickable { expanded = !expanded }
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    availableLanguages.forEach { (code, name) ->
                        DropdownMenuItem(
                            text = { Text(name) },
                            onClick = {
                                onLanguageSelected(code)
                                expanded = false
                            },
                            leadingIcon = if (code == currentLanguage) {
                                {
                                    Icon(
                                        imageVector = Icons.Default.ArrowDropDown,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                }
                            } else null
                        )
                    }
                }
            }
        }
    }
}