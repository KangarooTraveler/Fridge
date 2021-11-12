package com.example.fridge

import android.R
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.ListView
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_settings_actitivty_f.*
import kotlinx.android.synthetic.main.customadapter.*


class SettingsActitivtyF : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.fridge.R.layout.activity_settings_actitivty_f)

        val toolbar8: androidx.appcompat.widget.Toolbar = findViewById(com.example.fridge.R.id.toolbarR8)
        setSupportActionBar(toolbar8)
        title = ""
        supportActionBar?.setHomeAsUpIndicator(com.example.fridge.R.drawable.ic_back_fr)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}