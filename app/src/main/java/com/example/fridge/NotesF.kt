package com.example.fridge

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.fridge.databinding.ActivityNotesFBinding
import kotlinx.android.synthetic.main.activity_settings_actitivty_f.*
import kotlinx.android.synthetic.main.fragment_add_fragment.*

class NotesF : AppCompatActivity() {

    private lateinit var binding: ActivityNotesFBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // binding = ActivityNotesFBinding.inflate(layoutInflater)
     //   setContentView(binding.root)
        setContentView(R.layout.activity_notes_f)

        val toolbar: Toolbar = findViewById(R.id.toolbarR3)
        setSupportActionBar(toolbar)
        title = ""
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back_fr)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);
    }

    override fun onSupportNavigateUp(): Boolean {
       onBackPressed()
       return true
    }


}