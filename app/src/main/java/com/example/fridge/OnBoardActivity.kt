package com.example.fridge

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.example.fridge.MyProducts.ProductsFOpen
import com.example.fridge.fragments.list.ViewPagerAdapter
import com.example.fridge.modelF.OnBoardingData
import com.google.android.material.tabs.TabLayout

class OnBoardActivity : AppCompatActivity() {

    var viewPagerAdapter: ViewPagerAdapter? = null
    var tabLayout: TabLayout? = null
    var onBoardingViewPager: ViewPager? = null
    var next: TextView? = null
    var position = 0
    var sharedPreferences: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(restorePrefData()){
            val i = Intent(applicationContext, ProductsFOpen::class.java)
            startActivity(i)
            finish()
        }

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
      //  supportActionBar!!.hide()

        setContentView(R.layout.activity_on_board)

        tabLayout = findViewById(R.id.tabInR)
        next = findViewById(R.id.Next)

        val onBoardingData: MutableList<OnBoardingData> = ArrayList()
        onBoardingData.add(OnBoardingData("Продукты", "Добавляйте продукты для дистанционного мониторинга", R.drawable.holodilnik))
        onBoardingData.add(OnBoardingData("Рецепты", "Создавайте собственные рецепты по подбору продуктов", R.drawable.litdlyareceptov))
        onBoardingData.add(OnBoardingData("Поисковик сторонних рецептов", "Упрощайте поиск сторонних рецептов", R.drawable.poizk))

        setOnBoardingViewPagerAdapter(onBoardingData)

        position = onBoardingViewPager!!.currentItem

        next?.setOnClickListener{
            if (position<onBoardingData.size){
                position++
                onBoardingViewPager!!.currentItem = position
            }
            if (position == onBoardingData.size){
                savePrefData()
                val i = Intent(applicationContext, ProductsFOpen::class.java)
                startActivity(i)
                finish()

            }
        }

        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                position = p0!!.position
                if (p0.position == onBoardingData.size - 1){
                    next!!.text = "Начать"
                } else {
                    next!!.text = "Дальше"
                }
            }
        })

    }

    private fun setOnBoardingViewPagerAdapter(onBoardingData: List<OnBoardingData>){
        onBoardingViewPager = findViewById(R.id.screenR)
        viewPagerAdapter = ViewPagerAdapter(this,  onBoardingData)
        onBoardingViewPager!!.adapter = viewPagerAdapter
        tabLayout?.setupWithViewPager(onBoardingViewPager)
    }

    private fun savePrefData(){
        sharedPreferences = applicationContext.getSharedPreferences("pref", Context.MODE_PRIVATE)
        val editor = sharedPreferences!!.edit()
        editor.putBoolean("itsFirstTimeRun", true)
        editor.apply()
    }

    private fun restorePrefData(): Boolean{
        sharedPreferences = applicationContext.getSharedPreferences("pref", Context.MODE_PRIVATE)
        return sharedPreferences!!.getBoolean("itsFirstTimeRun", false)
    }
}