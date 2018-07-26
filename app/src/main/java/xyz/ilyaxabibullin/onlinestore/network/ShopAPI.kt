package xyz.ilyaxabibullin.onlinestore.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import xyz.ilyaxabibullin.onlinestore.entitys.retrofit.ShopResponse

interface ShopAPI {
    @GET("/shop")
    fun getShop(@Query("id")id:Int): Call<ShopResponse>
}