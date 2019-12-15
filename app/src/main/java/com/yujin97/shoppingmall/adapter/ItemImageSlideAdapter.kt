package com.yujin97.shoppingmall.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.yujin97.shoppingmall.R
import kotlinx.android.synthetic.main.item_slide.view.*

class ItemImageSlideAdapter(var imageList : List<String>?) : PagerAdapter() {
        private val TAG = "ItemImageSlideAdapter"

        override fun isViewFromObject(view: View, any: Any): Boolean {
            return view.equals(any)
        }

        override fun getCount(): Int {
            var count = 0
            imageList?.size?.let {
                count = it
            }
            return count
        }

        override fun destroyItem(container: ViewGroup, position: Int, oo: Any) {
            container.removeView(oo as View)
        }

        override fun getItemPosition(`object`: Any): Int {
            return super.getItemPosition(`object`)
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val inflater: LayoutInflater = LayoutInflater.from(container.context)
            val imageLayout = inflater.inflate(R.layout.item_slide, container, false)
            val imageView = imageLayout.slideImageVIew
            imageList?.get(position)?.let {
                Glide.with(container.context)
                    .load(it)
                    .into(imageView)
            }
            container.addView(imageLayout, 0)
            return imageLayout
        }
}