package com.yujin97.shoppingmall.restManager

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RestManager {
    internal val TAG = "OoRestManager"

    private const val BASE_URL = "https://2jt4kq01ij.execute-api.ap-northeast-2.amazonaws.com/prod/"
    private const val TOKEN = "063e4656708913575d4f3b7542dac753"

    private var isInit = false
    private lateinit var retrofit: Retrofit
    private lateinit var restService: RestService

    fun init() {
        if (isInit) {
            return
        }

        retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        restService = retrofit.create(
            RestService::class.java)
        isInit = true
    }

    fun getItemListOf(pageNum : Int, completion: (List<Response.ResponseBody>?) -> Unit) {
        restService.getPageInfo(pageNum).enqueue(object : Callback<Response.Response<List<Response.ResponseBody>>> {
            override fun onResponse(
                call: Call<Response.Response<List<Response.ResponseBody>>>,
                response: retrofit2.Response<Response.Response<List<Response.ResponseBody>>>
            ) {
                if (response.isSuccessful) {
                    Log.i(TAG, "getItemList body : ${response.body()}")
                    completion(response.body()?.body)
                } else {
                    Log.i(TAG, "getItemList failed")
                    completion(null)
                }
            }

            override fun onFailure(
                call: Call<Response.Response<List<Response.ResponseBody>>>,
                t: Throwable
            ) {
                Log.i(TAG, "restService not connected")
                completion(null)
            }
        })
    }

    fun getItemInfo(itemCode : Int, completion: (Response.ResponseItem?) -> Unit) {
        restService.getItemInfo(itemCode).enqueue(object : Callback<Response.Response<Array<Response.ResponseItem>>> {
            override fun onResponse(
                call: Call<Response.Response<Array<Response.ResponseItem>>>,
                response: retrofit2.Response<Response.Response<Array<Response.ResponseItem>>>
            ) {
                if (response.isSuccessful) {
                Log.i(TAG, "getItem body : ${response.body()}")
                completion(response.body()?.body?.first())
                } else {
                    Log.i(TAG, "getItem body failed")
                    completion(null)
                }
            }

            override fun onFailure(
                call: Call<Response.Response<Array<Response.ResponseItem>>>,
                t: Throwable
            ) {
                Log.i(TAG, "restService not connected")
                completion(null)
            }
        })
    }
}