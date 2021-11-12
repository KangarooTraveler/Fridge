package com.example.fridge

import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Bundle
import android.preference.Preference
import android.preference.PreferenceActivity
import androidx.preference.CheckBoxPreference
import androidx.preference.PreferenceManager


class PreferenceSettings : PreferenceActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(com.example.fridge.R.xml.prefs)
        Load_setting()
    }

    private fun Load_setting() {

    }

    override fun onResume() {
        Load_setting()
        super.onResume()
    }
}


