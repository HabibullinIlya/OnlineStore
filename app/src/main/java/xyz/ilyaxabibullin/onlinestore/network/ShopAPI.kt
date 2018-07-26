package xyz.ilyaxabibullin.onlinestore.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import xyz.ilyaxabibullin.onlinestore.App
import xyz.ilyaxabibullin.onlinestore.entitys.retrofit.ShopResponse

interface ShopAPI {
    @GET("/shop")
    fun getShop(@Query("id")id:Int):                         Call<ShopResponse>

    @POST("/shop")
    fun addShop(@Query("access_token") token:String = App.token,
                @Query("name") name:String,
                @Query("description") description:String):   Call<ShopResponse>
}