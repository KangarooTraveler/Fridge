package com.example.fridge.MyProducts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fridge.fragments.list.RecyclerViewAdapter
import com.example.fridge.fragments.list.RecyclerViewAdapter2
import com.example.fridge.modelF.ModelR
import com.yarolegovich.slidingrootnav.SlidingRootNav
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder

class ProductsFOpen : AppCompatActivity() {

    var slidingRootNav: SlidingRootNav? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.fridge.R.layout.activity_products_f)

        val toolbar: Toolbar = findViewById(com.example.fridge.R.id.toolbarR4)
        setSupportActionBar(toolbar)
        title = ""
        supportActionBar?.setHomeAsUpIndicator(com.example.fridge.R.drawable.ic_back_fr)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);

        //Добавление объектов в RecyclerView
        val arrayList = ArrayList<ModelR>()
        arrayList.add(ModelR("Мои рецепты", com.example.fridge.R.drawable.ic_library_booksf))
        arrayList.add(ModelR("Настройки", com.example.fridge.R.drawable.ic_baseline_settings_24))
        arrayList.add(ModelR("Использование", com.example.fridge.R.drawable.fridge_3199))

        val rAdapter = RecyclerViewAdapter(arrayList, this)

        val arrayList2 = ArrayList<ModelR>()
        arrayList2.add(ModelR("О программе", com.example.fridge.R.drawable.ic__info_f))
        arrayList2.add(ModelR("Выход", com.example.fridge.R.drawable.ic_f_exit_to_app_24))

        val rAdapter2 = RecyclerViewAdapter2(arrayList2, this)

        //Объявление и настройка анимационного меню
        slidingRootNav = SlidingRootNavBuilder(this)
            .withDragDistance(180)
            .withRootViewScale(0.75f)
            .withRootViewElevation(25)
            .withToolbarMenuToggle(toolbar)
            .withMenuOpened(false)
            .withContentClickableWhenMenuOpened(true)
            .withSavedState(savedInstanceState)
            .withMenuLayout(com.example.fridge.R.layout.drawer_menu)
            .inject()

        //Установленный параметр LinearLayout для отключения эффекта скроллинга
        val lm: LinearLayoutManager = object : LinearLayoutManager(this){
            override fun canScrollVertically(): Boolean {
                return false
            }
        }

        val lm2: LinearLayoutManager = object : LinearLayoutManager(this){
            override fun canScrollVertically(): Boolean {
                return false
            }
        }

        //Присвоение RecyclerView к адаптерам
        val listR = findViewById<RecyclerView>(com.example.fridge.R.id.drawer_List)
        listR.isNestedScrollingEnabled = false
        listR.layoutManager = lm
        listR.adapter = rAdapter

        val listR2 = findViewById<RecyclerView>(com.example.fridge.R.id.drawer_List2)
        listR2!!.isNestedScrollingEnabled = false
        listR2!!.layoutManager = lm2
        listR2!!.adapter = rAdapter2
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }



}