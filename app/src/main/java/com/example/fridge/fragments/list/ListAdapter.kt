package com.example.fridge.fragments.list

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.fridge.R
import com.example.fridge.WebViewActivity
import com.example.fridge.modelF.ReceptedF
import com.example.fridge.viewmodel.receptedViewModel
import kotlinx.android.synthetic.main.custom_row_fr.view.*


class ListAdapter(private var mReceptedViewModel:receptedViewModel, val context: Context): RecyclerView.Adapter<ListAdapter.MyViewHolder>() {


    private var ReceptedList = emptyList<ReceptedF>()

    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val viewFr = LayoutInflater.from(parent.context).inflate(R.layout.custom_row_fr, parent, false)
        return MyViewHolder(viewFr)
    }

    override fun getItemCount(): Int {
      return ReceptedList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = ReceptedList[position]

        holder.itemView.z_txt.text = currentItem.Name
        holder.itemView.op_txt.text = currentItem.Opisanie
        holder.itemView.color_Text.text = currentItem.ColorR

        if (holder.itemView.color_Text.text == "Color1"){
            holder.itemView.op_txt.visibility = View.GONE
            holder.itemView.z_txt.setTextColor(Color.parseColor("#ff0000"))
        }

        holder.itemView.rowMainLayout.setOnClickListener{
            if(holder.itemView.color_Text.text == "Color1"){
                val value = holder.itemView.op_txt.text
                val i = Intent(this.context, WebViewActivity::class.java)
                i.putExtra("url", value)
                context.startActivity(i)
            } else {
                val action = listFragmentFDirections.actionListFragmentFToUpdateFragmentF(currentItem)
                holder.itemView.findNavController().navigate(action)
            }

        }

       holder.itemView.layout_deleteF.setOnClickListener {
           //Удаление записи в списке "Мои рецепты"
           Toast.makeText(context.applicationContext, "Запись рецепта удалена", Toast.LENGTH_SHORT).show()
           mReceptedViewModel.deleteRecepted(currentItem)
       }
    }

    fun setData(recepted: List<ReceptedF>){
        this.ReceptedList = recepted
        notifyDataSetChanged()
    }
}