package com.example.fridge.fragments.list

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fridge.*
import com.example.fridge.MyProducts.ProductsFOpen
import com.example.fridge.modelF.ModelR
import kotlinx.android.synthetic.main.item_option.view.*
import java.util.*


class RecyclerViewAdapter(val arrayList: ArrayList<ModelR>, val context: Context): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    var timer: Timer? = null

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        fun bindItems (model: ModelR){
            itemView.titleD.text = model.title
            itemView.iconD.setImageResource(model.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_option, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(arrayList[position])

        holder.itemView.setOnClickListener {
            if (position == 0) {
                (context as ProductsFOpen).slidingRootNav?.closeMenu()
                    timer = Timer()
                    timer!!.schedule(object : TimerTask() {
                        override fun run() {
                            val noteIntent = Intent(context, NotesF::class.java)
                            context.startActivity(noteIntent)
                        }
                    }, 300)
                }
                if (position == 1) {
                    (context as ProductsFOpen).slidingRootNav?.closeMenu()
                    timer = Timer()
                    timer!!.schedule(object : TimerTask() {
                        override fun run() {
                            val spisok = Intent(context, SettingsActitivtyF::class.java)
                            context.startActivity(spisok)
                        }
                    }, 300)

                }
                if (position == 2) {
                    (context as ProductsFOpen).slidingRootNav?.closeMenu()
                    timer = Timer()
                    timer!!.schedule(object : TimerTask() {
                        override fun run() {
                            val aIntent = Intent(context, Applying::class.java)
                            context.startActivity(aIntent)
                        }
                    }, 300)
                }

            }
        }

    }