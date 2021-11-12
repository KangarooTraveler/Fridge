package com.example.fridge.fragments.list


import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toolbar
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.fridge.MyProducts.ProductsFOpen
import com.example.fridge.R
import com.example.fridge.modelF.ProductsF
import kotlinx.android.synthetic.main.activity_products_f.view.*
import kotlinx.android.synthetic.main.custom_row_productrf.view.*
import kotlinx.android.synthetic.main.fragment_add_fragmentfor_products.view.*
import java.text.SimpleDateFormat
import java.util.*

class ListAdapterForProducts(var context: Context): RecyclerView.Adapter<ListAdapterForProducts.MyViewHolder>() {

    private var productList = emptyList<ProductsF>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val viewPr = LayoutInflater.from(parent.context).inflate(R.layout.custom_row_productrf, parent, false)
        val imgPrF: ImageView = viewPr.findViewById(R.id.imgFProducts)
        imgPrF.visibility = View.INVISIBLE
        return MyViewHolder(viewPr)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = productList[position]
        holder.itemView.zProd_txt.text = currentItem.nameP
        holder.itemView.kolvoP.text = "Количество: " + currentItem.countP.toString()
        holder.itemView.DatePF.text = currentItem.dateP
        holder.itemView.CountD.text = currentItem.countD.toString()

        if(holder.itemView.CountD.text == "0"){
            holder.itemView.CountD.visibility = View.GONE
        }

        if (holder.itemView.zProd_txt.text == "Яблоко" || holder.itemView.zProd_txt.text == "яблоко"){
            holder.itemView.imgFProducts.visibility = View.VISIBLE
            holder.itemView.imgFProducts.setImageResource(R.drawable.apple)
        }
        if (holder.itemView.zProd_txt.text == "Банан" || holder.itemView.zProd_txt.text == "банан"){
            holder.itemView.imgFProducts.visibility = View.VISIBLE
            holder.itemView.imgFProducts.setImageResource(R.drawable.banana)
        }
        if (holder.itemView.zProd_txt.text == "Ананас" || holder.itemView.zProd_txt.text == "ананас"){
            holder.itemView.imgFProducts.visibility = View.VISIBLE
            holder.itemView.imgFProducts.setImageResource(R.drawable.pineapple)
        }
        if (holder.itemView.zProd_txt.text == "Апельсин" || holder.itemView.zProd_txt.text == "апельсин"){
            holder.itemView.imgFProducts.visibility = View.VISIBLE
            holder.itemView.imgFProducts.setImageResource(R.drawable.orange)
        }
        if (holder.itemView.zProd_txt.text == "Арбуз" || holder.itemView.zProd_txt.text == "арбуз"){
            holder.itemView.imgFProducts.visibility = View.VISIBLE
            holder.itemView.imgFProducts.setImageResource(R.drawable.watermelon)
        }
        if (holder.itemView.zProd_txt.text == "Лук" || holder.itemView.zProd_txt.text == "лук"){
            holder.itemView.imgFProducts.visibility = View.VISIBLE
            holder.itemView.imgFProducts.setImageResource(R.drawable.onion)
        }
        if (holder.itemView.zProd_txt.text == "Баклажан" || holder.itemView.zProd_txt.text == "баклажан"){
            holder.itemView.imgFProducts.visibility = View.VISIBLE
            holder.itemView.imgFProducts.setImageResource(R.drawable.eggplant)
        }
        if (holder.itemView.zProd_txt.text == "Яйцо" || holder.itemView.zProd_txt.text == "яйцо"){
            holder.itemView.imgFProducts.visibility = View.VISIBLE
            holder.itemView.imgFProducts.setImageResource(R.drawable.egg)
        }
        if (holder.itemView.zProd_txt.text == "Морковь" || holder.itemView.zProd_txt.text == "морковь"){
            holder.itemView.imgFProducts.visibility = View.VISIBLE
            holder.itemView.imgFProducts.setImageResource(R.drawable.carrot)
        }
        if (holder.itemView.zProd_txt.text == "Томат" || holder.itemView.zProd_txt.text == "томат"){
            holder.itemView.imgFProducts.visibility = View.VISIBLE
            holder.itemView.imgFProducts.setImageResource(R.drawable.tomate)
        }
        if (holder.itemView.zProd_txt.text == "Молоко" || holder.itemView.zProd_txt.text == "молоко"){
            holder.itemView.imgFProducts.visibility = View.VISIBLE
            holder.itemView.imgFProducts.setImageResource(R.drawable.milk2)
        }
        if (holder.itemView.zProd_txt.text == "Чеснок" || holder.itemView.zProd_txt.text == "чеснок"){
            holder.itemView.imgFProducts.visibility = View.VISIBLE
            holder.itemView.imgFProducts.setImageResource(R.drawable.garlic)
        }
        if (holder.itemView.zProd_txt.text == "Рыба" || holder.itemView.zProd_txt.text == "рыба"){
            holder.itemView.imgFProducts.visibility = View.VISIBLE
            holder.itemView.imgFProducts.setImageResource(R.drawable.fish)
        }
        if (holder.itemView.zProd_txt.text == "Консервы" || holder.itemView.zProd_txt.text == "консервы"){
            holder.itemView.imgFProducts.visibility = View.VISIBLE
            holder.itemView.imgFProducts.setImageResource(R.drawable.cannedfood)
        }

        holder.itemView.rowProduct.setOnClickListener {
            val action = ListFragmentForProductsFDirections.actionListFragmentForProductsFToUpdateFragmentProducts(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }


    fun setData(products: List<ProductsF>){
        this.productList = products
        notifyDataSetChanged()
    }
}