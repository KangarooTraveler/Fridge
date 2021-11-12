package com.example.fridge.MyProducts

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.fridge.R

class ProductsFAdapter(var context: Context, var arrayList: ArrayList<ProductsGr>): BaseAdapter() {

    //Установка массива объектов по позициям
    override fun getItem(position: Int): Any {
        return arrayList[position]
    }

    //Установка позиции объектов
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    //Установка размера GridView по массиву объектов из класса ProductsGr
    override fun getCount(): Int {
        return arrayList.size
    }

    //Получение объектов GridView в разделе "Мои продукты" ProductsF
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view: View = View.inflate(context, R.layout.productsgrf, null)

        var icons: ImageView = view.findViewById(R.id.imgGrProducts)
        var names: TextView = view.findViewById(R.id.tvName)

        var productsItem: ProductsGr = arrayList.get(position)

        icons.setImageResource(productsItem.image!!)
        names.text = productsItem.name

        return view
    }
}