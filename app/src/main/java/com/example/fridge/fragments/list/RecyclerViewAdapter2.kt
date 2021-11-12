package com.example.fridge.fragments.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.fridge.DialogFragmentF
import com.example.fridge.MyProducts.ProductsFOpen
import com.example.fridge.R
import com.example.fridge.modelF.ModelR
import kotlinx.android.synthetic.main.item_option.view.*


class RecyclerViewAdapter2(val arrayList2: ArrayList<ModelR>, val context: Context):  RecyclerView.Adapter<RecyclerViewAdapter2.ViewHolder>() {

    var mContext: Context? = null

    fun SubjectsAdapter(context: Context) {
// Here we're getting the activity's context,
// by setting the adapter on the activity with (this)
        this.mContext = context
    }
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
        return arrayList2.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(arrayList2[position])

        holder.itemView.setOnClickListener {
            if(position == 0){
                val newFragment: DialogFragmentF = DialogFragmentF().newInstance()!!
                newFragment.show(
                    (context as AppCompatActivity).supportFragmentManager,
                    "Title")
                (context as ProductsFOpen).slidingRootNav?.closeMenu()
            //   newFragment.show(context, "Title")
//               val IntentMessage = Intent(this.context, MainActivity::class.java)
//                IntentMessage.putExtra("sup", "qq")
//                LocalBroadcastManager.getInstance(context).sendBroadcast(IntentMessage);
          //      Toast.makeText(this.context, "Временно недоступно.", Toast.LENGTH_SHORT).show()
            }
            if(position == 1){
                System.exit(0)
            }
        }
    }
}