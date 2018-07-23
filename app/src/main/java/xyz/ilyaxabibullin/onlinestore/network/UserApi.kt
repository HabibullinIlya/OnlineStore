package xyz.ilyaxabibullin.onlinestore.network

import retrofit2.Call
import retrofit2.http.*
import xyz.ilyaxabibullin.onlinestore.entitys.retrofit.Response
import xyz.ilyaxabibullin.onlinestore.entitys.retrofit.AuthResponse
import xyz.ilyaxabibullin.onlinestore.entitys.retrofit.UserResponse

interface UserApi {
    @POST("/users")
    fun auth(@Query("username") login: String,
             @Query("password") password: String):              Call<AuthResponse>

    @GET("/users")
    fun getUser(@Query("id") id: Int):                          Call<UserResponse>

    @PUT("/users")
    fun updateUser(@Query("id") id: Int,
                   @Query("token") token: String):              Call<UserResponse>

    @DELETE("/users")
    fun deleteUser(@Query("id") id: Int,
                   @Query("access_token") token: String):       Call<Response>


}
