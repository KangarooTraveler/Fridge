package com.example.fridge

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_web_view.*


class WebViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        webViewSetup1()

    }

    //Загрузка присланных ссылок
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetJavaScriptEnabled")
    private fun webViewSetup1(){
        //Мясо по французки
        wb_view1.webViewClient = WebViewClient()
        wb_view1.apply {
            val url = intent.extras!!.getString("url")
            if (url != null) {
                wb_view1.loadUrl(url)
            }
         //   loadUrl("https://art-lunch.ru/recipe/myaso-po-francuzski_foto/")
            settings.javaScriptEnabled = true
            settings.safeBrowsingEnabled = true
        }
    }


}