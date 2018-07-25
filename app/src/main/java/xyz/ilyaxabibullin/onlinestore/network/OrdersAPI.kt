package xyz.ilyaxabibullin.onlinestore.network

import retrofit2.Call
import retrofit2.http.*
import xyz.ilyaxabibullin.onlinestore.App
import xyz.ilyaxabibullin.onlinestore.entitys.retrofit.CartResponse
import xyz.ilyaxabibullin.onlinestore.entitys.retrofit.OrderResponse
import xyz.ilyaxabibullin.onlinestore.entitys.retrofit.OrdersListResponse
import xyz.ilyaxabibullin.onlinestore.entitys.retrofit.Response

interface OrdersAPI{
    @GET("/orders")
    fun getOrder(@Query("access_token")token: String = App.token):   Call<OrderResponse>


    @POST("/orders")
    fun addOrder(@Query("access_token")token:String = App.token):   Call<OrderResponse>

    @POST("/orders/all")
    fun allOrders(@Query("access_token")token:String = App.token,
                  @Query("page") page:Int = 1,
                  @Query("limit")limit:Int = 15):                   Call<OrdersListResponse>


    @DELETE("orders")
    fun deleteOrder(@Query("access_token")token:String = App.token,
                    @Query("id")id:Int):                            Call<OrderResponse>
}