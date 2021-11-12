package com.example.fridge

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fridge.fragments.list.RecyclerViewAdapter
import com.example.fridge.fragments.list.RecyclerViewAdapter2
import com.example.fridge.modelF.ModelR
import com.yarolegovich.slidingrootnav.SlidingRootNav
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity: AppCompatActivity(){

    private lateinit var adapter: ArrayAdapter<*>

   // var slidingRootNav: SlidingRootNav? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = ""
        val toolbar: Toolbar = findViewById(R.id.toolbarR)
        setSupportActionBar(toolbar)

        //Возврат метода onOptionsItemSelected
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back_fr)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);

        adapter = ArrayAdapter(this,  R.layout.listviewcontent, R.id.zListViewContent_txt, resources.getStringArray(R.array.Bluda_array))
        lv_listView.adapter = adapter

        val randomIntent = Intent(this, WebViewActivity::class.java)

        lv_listView.onItemClickListener = AdapterView.OnItemClickListener{parent, view, position, id ->
           //Вывод текстового сообщения при нажатии (В данный момент отключено, т.к строка вывода переведена в кликабельный текст)
           Toast.makeText(applicationContext, parent?.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show()
        }
        lv_listView.emptyView = tv_emptyTextView

        val lv1 = findViewById<ListView>(R.id.lv_listView)

       // lv_listView.setBackgroundColor(colorF)

        val sharedPrefs = getSharedPreferences("SHARED_f", Context.MODE_PRIVATE)

            val bundle = intent.extras
            if (bundle != null) {
                lv1.setBackgroundColor(bundle.getInt("COLOR_1"))

            }

        //Реализация открытия другого окна при нажатии на один из объектов
        lv_listView.setOnItemClickListener(object : AdapterView.OnItemClickListener{
            override fun onItemClick(
                parent: AdapterView<*>?,
                itemClicked: View?,
                position: Int,
                id: Long
            ) {
           //     val textView = itemClicked as TextView
                val strText = parent?.getItemAtPosition(position).toString()//textView.text.toString()
                //Условия на проверку выведенного текста
                if(strText.equals("Рататуй", ignoreCase = true)){
                    randomIntent.putExtra("url", "https://www.russianfood.com/recipes/recipe.php?rid=157974")
                    startActivity(randomIntent)
                }
                if(strText.equals("Мясо по французки", ignoreCase = true))
                {
                    randomIntent.putExtra("url", "https://art-lunch.ru/recipe/myaso-po-francuzski_foto/")
                    startActivity(randomIntent)
                }
                if(strText.equals("Печеная курица", ignoreCase = true))
                {
                    randomIntent.putExtra("url", "https://www.russianfood.com/recipes/recipe.php?rid=8069")
                    startActivity(randomIntent)
                }
                if(strText.equals("Салат Цезарь", ignoreCase = true))
                {
                    randomIntent.putExtra("url", "https://www.russianfood.com/recipes/recipe.php?rid=119006")
                    startActivity(randomIntent)
                }
                if(strText.equals("Бургеры с лососем и яйцами-пашот", ignoreCase = true))
                {
                    randomIntent.putExtra("url", "https://www.russianfood.com/recipes/recipe.php?rid=123627")
                    startActivity(randomIntent)
                }
                if(strText.equals("Соус Тартар", ignoreCase = true))
                {
                    randomIntent.putExtra("url", "https://www.russianfood.com/recipes/recipe.php?rid=133956")
                    startActivity(randomIntent)
                }
                if(strText.equals("Чесночный соус", ignoreCase = true))
                {
                    randomIntent.putExtra("url", "https://www.russianfood.com/recipes/recipe.php?rid=142080")
                    startActivity(randomIntent)
                }
                if(strText.equals("Соус бешамель", ignoreCase = true))
                {
                    randomIntent.putExtra("url", "https://www.russianfood.com/recipes/recipe.php?rid=12368")
                    startActivity(randomIntent)
                }
                if(strText.equals("Соус Биг Тейсти", ignoreCase = true))
                {
                    randomIntent.putExtra("url", "https://www.russianfood.com/recipes/recipe.php?rid=160443")
                    startActivity(randomIntent)
                }
                if(strText.equals("Томатно-чесночный соус", ignoreCase = true))
                {
                    randomIntent.putExtra("url", "https://www.russianfood.com/recipes/recipe.php?rid=144470")
                    startActivity(randomIntent)
                }
                if(strText.equals("Салат с крабовыми палочками", ignoreCase = true))
                {
                    randomIntent.putExtra("url", "https://www.russianfood.com/recipes/recipe.php?rid=152977&ref=cro_t_2&token=1620721146")
                    startActivity(randomIntent)
                }
                if(strText.equals("Салат из тунца", ignoreCase = true))
                {
                    randomIntent.putExtra("url", "https://www.russianfood.com/recipes/recipe.php?rid=156002")
                    startActivity(randomIntent)
                }
                if(strText.equals("Пирог с брокколи и курицей", ignoreCase = true))
                {
                    randomIntent.putExtra("url", "https://www.russianfood.com/recipes/recipe.php?rid=124082&ref=cro_t_4&token=142026477")
                    startActivity(randomIntent)
                }
                if(strText.equals("Суп из чечевицы с копченостями", ignoreCase = true))
                {
                    randomIntent.putExtra("url", "https://www.russianfood.com/recipes/recipe.php?rid=126185")
                    startActivity(randomIntent)
                }
                if(strText.equals("Грибной суп-пюре с сырными гренками", ignoreCase = true))
                {
                    randomIntent.putExtra("url", "https://www.russianfood.com/recipes/recipe.php?rid=128327")
                    startActivity(randomIntent)
                }
                if(strText.equals("Хачапури по-аджарски", ignoreCase = true))
                {
                    randomIntent.putExtra("url", "https://www.russianfood.com/recipes/recipe.php?rid=146241&ref=cro_t_4&token=67766518")
                    startActivity(randomIntent)
                }
                if(strText.equals("Филе трески в соусе из белого вина, с томатами черри и базиликом", ignoreCase = true))
                {
                    randomIntent.putExtra("url", "https://www.russianfood.com/recipes/recipe.php?rid=144530&ref=cro_t_7&token=1980118377")
                    startActivity(randomIntent)
                }
                if(strText.equals("Бисквитный рулет с бананами и вареньем", ignoreCase = true))
                {
                    randomIntent.putExtra("url", "https://www.russianfood.com/recipes/recipe.php?rid=136920")
                    startActivity(randomIntent)
                }
                if(strText.equals("Яблочно-грушевый крамбл с мёдом и орехами", ignoreCase = true))
                {
                    randomIntent.putExtra("url", "https://www.russianfood.com/recipes/recipe.php?rid=153706")
                    startActivity(randomIntent)
                }
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    //Реализация работы и изображения иконки поиска
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu, menu)
        val search = menu?.findItem(R.id.nav_search);
        val searchView = search?.actionView as SearchView

        searchView.queryHint = "Поиск блюд"
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }




}








