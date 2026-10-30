package com.kfaraj.samples.darktheme.ui

import android.content.SharedPreferences.OnSharedPreferenceChangeListener
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.edit
import com.kfaraj.samples.darktheme.R
import com.kfaraj.samples.darktheme.data.Settings
import com.kfaraj.samples.darktheme.ui.theme.AppTheme

/**
 * Displays the settings UI state on the screen.
 */
@Composable
fun SettingsScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var theme by remember { mutableStateOf<String?>(null) }
    DisposableEffect(context) {
        val listener = OnSharedPreferenceChangeListener { sharedPreferences, _ ->
            theme = sharedPreferences.getString(
                Settings.KEY_THEME,
                Settings.THEME_SYSTEM_DEFAULT
            )
        }
        val prefs = Settings.getDefaultSharedPreferences(context)
        prefs.registerOnSharedPreferenceChangeListener(listener)
        listener.onSharedPreferenceChanged(prefs, null)
        onDispose {
            prefs.unregisterOnSharedPreferenceChangeListener(listener)
        }
    }
    SettingsScreen(
        onBackClick = onBackClick,
        theme = theme,
        onThemeChange = { newTheme ->
            val prefs = Settings.getDefaultSharedPreferences(context)
            prefs.edit {
                putString(Settings.KEY_THEME, newTheme)
            }
        },
        modifier = modifier
    )
}

/**
 * Displays the settings UI state on the screen.
 */
@Composable
private fun SettingsScreen(
    onBackClick: () -> Unit,
    theme: String?,
    onThemeChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.settings)
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_arrow_back),
                            contentDescription = stringResource(R.string.back)
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) { innerPadding ->
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(innerPadding)
        ) {
            ListPreference(
                title = stringResource(R.string.theme_title),
                entries = stringArrayResource(R.array.theme_entries),
                entryValues = arrayOf(
                    Settings.THEME_LIGHT,
                    Settings.THEME_DARK,
                    Settings.THEME_SYSTEM_DEFAULT
                ),
                value = theme,
                onValueChange = onThemeChange
            )
        }
    }
}

@Preview
@Composable
private fun SettingsScreenPreview() {
    AppTheme {
        var theme by remember { mutableStateOf(Settings.THEME_SYSTEM_DEFAULT) }
        SettingsScreen(
            onBackClick = {},
            theme = theme,
            onThemeChange = { theme = it }
        )
    }
}
