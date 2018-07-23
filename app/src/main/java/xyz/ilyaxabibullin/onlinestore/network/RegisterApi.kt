package xyz.ilyaxabibullin.onlinestore.network

import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query
import xyz.ilyaxabibullin.onlinestore.entitys.retrofit.UserResponse

interface RegisterApi{
    @POST("/register")
    fun register(@Query("email") email:String,
                 @Query("first_name")firstName:String,
                 @Query("last_name")lastName:String,
                 @Query("password")password:String): Call<UserResponse>
}