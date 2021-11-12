package com.example.fridge

import android.R
import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.activity_applying.*

class Applying : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.fridge.R.layout.activity_applying)

        val toolBarApp: Toolbar = findViewById(com.example.fridge.R.id.toolbarApplying)
        setSupportActionBar(toolBarApp)

        title = ""
        supportActionBar?.setHomeAsUpIndicator(com.example.fridge.R.drawable.ic_back_fr)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);

        val text1 = this.resources.getString(com.example.fridge.R.string.Text1)
        textView5.setText(Html.fromHtml(text1))
        val text2 = this.resources.getString(com.example.fridge.R.string.Text2)
        textView6.setText(Html.fromHtml(text2))
        val text3 = this.resources.getString(com.example.fridge.R.string.Text3)
        textView7.setText(Html.fromHtml(text3))

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}