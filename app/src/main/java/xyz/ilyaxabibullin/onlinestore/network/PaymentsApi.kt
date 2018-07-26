package xyz.ilyaxabibullin.onlinestore.network


import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query
import xyz.ilyaxabibullin.onlinestore.App
import xyz.ilyaxabibullin.onlinestore.entitys.retrofit.Response

interface PaymentsApi {
    @POST("/payments")
    fun payments(@Query("access_token") token:String = App.token,
                 @Query("id")id:Int): Call<Response>
}