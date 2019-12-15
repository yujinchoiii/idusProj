package com.yujin97.shoppingmall.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.yujin97.shoppingmall.R
import com.yujin97.shoppingmall.restManager.Response
import kotlinx.android.synthetic.main.item_item.view.*

class ThumbnailAdapter(private val thumbnailList : List<Response.ResponseBody>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val TAG = "ThumbnailAdapter"
    var onItemClickedListener : OnItemClickedListener? = null
    var onLastItem : OnLastItem? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return thumbnailViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_item, parent, false))
    }

    override fun getItemCount(): Int {
        return thumbnailList?.size ?: 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position == itemCount-1) {
            onLastItem?.let {
                it()
            }
        }
        val viewHolder = holder as? thumbnailViewHolder
        viewHolder?.let {vh->
            thumbnailList?.get(position)?.apply {
                vh.thumbnailVIew.setOnClickListener {
                    val anim = AnimationUtils.loadAnimation(holder.itemView.context,
                        R.anim.anim_translate
                    )
                    anim.setAnimationListener(object : Animation.AnimationListener {
                        override fun onAnimationRepeat(animation: Animation?) {
                        }
                        override fun onAnimationEnd(animation: Animation?) {
                            onItemClickedListener?.let {
                                it(id)
                            }
                        }
                        override fun onAnimationStart(animation: Animation?) {
                        }
                    })
                    it.startAnimation(anim)
                }
                vh.name.text = title
                vh.seller.text = seller
                Glide.with(holder.itemView.context)
                    .load(thumbnail_520)
                    .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(14)))
                    .into(vh.thumbnail)
            }
        }
    }

    inner class thumbnailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var thumbnailVIew = itemView.thumbnailView
        var thumbnail = itemView.item_thumbnail
        var name = itemView.item_name
        var seller = itemView.item_seller
    }
}
typealias OnItemClickedListener = (Int) -> Unit
typealias OnLastItem = () -> Unit