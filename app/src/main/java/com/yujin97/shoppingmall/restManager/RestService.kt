package com.yujin97.shoppingmall.restManager

import com.yujin97.shoppingmall.restManager.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RestService {
    @GET("products")
    fun getPageInfo(@Query("page") roomId: Int): Call<Response.Response<List<Response.ResponseBody>>>
    @GET("products/{item_code}")
    fun getItemInfo(@Path("item_code") code: Int): Call<Response.Response<Array<Response.ResponseItem>>>
}