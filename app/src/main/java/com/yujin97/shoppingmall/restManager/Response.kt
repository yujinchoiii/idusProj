package com.yujin97.shoppingmall.restManager

import java.io.Serializable

class Response {
    open class Response<T>(val statusCode: String?, val body: T) : Serializable {
        fun isSuccess(): Boolean {
            return statusCode == "ok"
        }

        override fun toString(): String {
            return "Response(statusCode=$statusCode, body=$body)"
        }
    }

    open class ResponseBody(val id : Int, val thumbnail_520 : String, val title : String, val seller : String) : Serializable {
        override fun toString(): String {
            return "ResponseBody(id=$id, thumbnail_520=$thumbnail_520, title=$title, seller=$seller)"
        }
    }

    open class ResponseItem(val id : Int, val thumbnail_720 : String, val thumbnail_list_320 : String, val title : String, val seller: String, val cost : String, val discount_cost : String, val discount_rate : String, val description : String) : Serializable {
        override fun toString(): String {
            return "ResponseItem(id=$id, thumbnail_720='$thumbnail_720', thumbnaillist320='$thumbnail_list_320', title='$title', seller='$seller', cost='$cost', discount_cost='$discount_cost', discount_rate='$discount_rate', description='$description')"
        }
    }
}