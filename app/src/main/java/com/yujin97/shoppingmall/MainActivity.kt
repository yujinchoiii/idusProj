package com.yujin97.shoppingmall

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.yujin97.shoppingmall.adapter.ThumbnailAdapter
import com.yujin97.shoppingmall.restManager.Response
import com.yujin97.shoppingmall.restManager.RestManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    private var itemList = mutableListOf<Response.ResponseBody>()
    private var pageCount = 1
    private lateinit var thumbnailAdapter : ThumbnailAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RestManager.init()
        initWidget()
    }

    private fun initWidget() {
        getList {
            thumbnailAdapter = ThumbnailAdapter(itemList)
            thumbnailAdapter.onItemClickedListener = {itemCode ->
                Log.i(TAG, "onItemClicked")
                val intent = Intent(applicationContext, ItemActivity::class.java)
                intent.putExtra("itemCode",itemCode)
                startActivity(intent)
            }
            thumbnailAdapter.onLastItem = {
                getList {
                    thumbnailAdapter.notifyDataSetChanged()
                }
            }
            itemRecyclerView.adapter = thumbnailAdapter
        }
    }
    private fun getList(completion : ()-> Unit) {
        RestManager.getItemListOf(pageCount) { response ->
            pageCount++
            response?.let { res ->
                res.forEach { body ->
                    itemList.add(body)
                }
                completion()
            }
        }
    }
}
