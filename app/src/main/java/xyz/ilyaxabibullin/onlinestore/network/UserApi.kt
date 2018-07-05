package xyz.ilyaxabibullin.onlinestore.network

import retrofit2.Call
import retrofit2.http.*
import xyz.ilyaxabibullin.onlinestore.entitys.ResultQuery
import xyz.ilyaxabibullin.onlinestore.entitys.Token
import xyz.ilyaxabibullin.onlinestore.entitys.User

interface UserApi {
    @POST("/users")
    fun auth(@Query("login") login: String, @Query("password") password: String): Call<Token>

    @GET("/users")
    fun getUser(@Query("id") id: Int): Call<User>

    @PUT("/users")
    fun updateUser(@Query("id") id: Int, @Query("token") token: String): Call<User>//не полное описание запроса

    @DELETE("/users")
    fun deleteUser(@Query("id") id: Int, @Query("token") token: String): Call<ResultQuery>


}