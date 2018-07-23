package xyz.ilyaxabibullin.onlinestore.network

import okhttp3.Response
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

interface PaymentsApi {
    @POST("/payments")
    fun payments(@Query("access_token") token:String,
                 @Query("id")id:Int): Call<Response>
}