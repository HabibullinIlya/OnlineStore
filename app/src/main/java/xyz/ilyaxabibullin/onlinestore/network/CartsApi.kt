package xyz.ilyaxabibullin.onlinestore.network

import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import xyz.ilyaxabibullin.onlinestore.entitys.retrofit.CartResponse

interface CartsApi{
    @POST("/carts")
    fun addToCart(@Query("token")token:String,
                  @Query("id")id:Int): Call<CartResponse>

    @GET("/carts")
    fun getCartInfo(@Query("token")token:String):Call<CartResponse>

    @DELETE("/carts")
    fun deleteFromCart(@Query("token")token: String,
                       @Query("id")id:Int):Call<CartResponse>
}