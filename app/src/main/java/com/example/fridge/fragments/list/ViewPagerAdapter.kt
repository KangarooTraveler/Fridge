package com.example.fridge.fragments.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.example.fridge.R
import com.example.fridge.modelF.OnBoardingData
import java.util.*

class ViewPagerAdapter(private var context: Context, private var OnBoardingDataList: List<OnBoardingData>): PagerAdapter() {
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
       return view == `object`
    }

    override fun getCount(): Int {
        return OnBoardingDataList.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view: View = LayoutInflater.from(context).inflate(R.layout.onboarding_screen_layout, null)
        val imageView: ImageView
        val title: TextView
        val desc: TextView

        imageView = view.findViewById(R.id.ImgOnB)
        title = view.findViewById(R.id.titleOnB)
        desc = view.findViewById(R.id.titleOnB2)

        imageView.setImageResource(OnBoardingDataList[position].imageR)
        title.text = OnBoardingDataList[position].title
        desc.text = OnBoardingDataList[position].desc

        container.addView(view)
        return view
    }
}