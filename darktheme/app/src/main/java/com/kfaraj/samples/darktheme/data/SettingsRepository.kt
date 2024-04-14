package com.kfaraj.samples.darktheme.data

import android.content.Context
import androidx.preference.PreferenceManager
import com.kfaraj.samples.darktheme.R

/**
 * Exposes settings data.
 */
class SettingsRepository private constructor(
    context: Context
) {

    private val prefs = PreferenceManager.getDefaultSharedPreferences(context)

    init {
        PreferenceManager.setDefaultValues(context, R.xml.preferences, true)
    }

    /**
     * Returns the theme.
     */
    fun getTheme(defValue: String?): String? {
        return prefs.getString(Settings.THEME, defValue)
    }

    companion object {

        private var instance: SettingsRepository? = null

        /**
         * Returns the [SettingsRepository] instance.
         */
        fun getInstance(
            context: Context
        ): SettingsRepository {
            return instance ?: SettingsRepository(context).also { instance = it }
        }

    }

}
