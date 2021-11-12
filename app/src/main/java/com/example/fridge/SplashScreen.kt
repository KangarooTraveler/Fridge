package com.example.fridge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.ActionMode
import android.view.Window
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreen : AppCompatActivity() {
    lateinit var logo: TextView
  //  lateinit var slogan:TextView
    lateinit var myimage: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash_screen)

        val topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        val bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation)

        myimage = findViewById(R.id.imageView)
        logo = findViewById(R.id.textView)
        //slogan = findViewById(R.id.textView2)

        myimage.startAnimation(topAnim)
        logo.startAnimation(bottomAnim)
       //slogan.startAnimation(bottomAnim)

        Handler().postDelayed({
            startActivity(Intent(this@SplashScreen, OnBoardActivity::class.java))
            finish()
        }, 5000)


    }
}