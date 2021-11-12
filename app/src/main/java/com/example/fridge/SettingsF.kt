package com.example.fridge

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat

class SettingsF : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}