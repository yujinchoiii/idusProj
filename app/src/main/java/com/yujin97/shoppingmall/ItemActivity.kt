package com.yujin97.shoppingmall

import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.yujin97.shoppingmall.adapter.ItemImageSlideAdapter
import com.yujin97.shoppingmall.restManager.RestManager
import kotlinx.android.synthetic.main.activity_item.*

class ItemActivity : AppCompatActivity() {
    private var itemCode = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)

        itemCode = intent.getIntExtra("itemCode", 0)
        RestManager.init()
        initView()
    }

    private fun initView() {
        closePage.setOnClickListener { finish() }
        buyItem.isClickable = false
        RestManager.getItemInfo(itemCode) { item ->
            item?.apply{
                imageVIewPager.adapter = ItemImageSlideAdapter(
                    thumbnail_list_320.split("#")
                )
                pagerIndicator.setupWithViewPager(imageVIewPager, true)
                itemSeller.text = seller
                itemName.text = title
                discountRate.text = discount_rate
                discountCost.text = discount_cost
                if (!discount_cost.isNullOrEmpty()) {
                    itemCost.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                    itemCost.isActivated = false
                }
                itemCost.text = cost
                itemDescription.text = description
            }
        }
    }
}
