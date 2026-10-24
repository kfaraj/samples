package com.kfaraj.samples.darktheme

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.kfaraj.samples.darktheme.ui.SettingsScreen
import com.kfaraj.samples.darktheme.ui.theme.AppTheme

/**
 * Contains the [SettingsScreen].
 */
class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                SettingsScreen(
                    onBackClick = {
                        onNavigateUp()
                    }
                )
            }
        }
    }

}
