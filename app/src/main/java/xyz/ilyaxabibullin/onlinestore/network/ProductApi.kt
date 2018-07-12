package xyz.ilyaxabibullin.onlinestore.network

import retrofit2.Call
import retrofit2.http.*
import xyz.ilyaxabibullin.onlinestore.entitys.retrofit.Product
import xyz.ilyaxabibullin.onlinestore.entitys.retrofit.Response

interface ProductApi {
    @POST("/products")
    fun addProducts(@Query("token")token:String,
                    @Query("name") name: String,
                    @Query("price") price: Double,
                    @Query("description") description: String): Call<Response>//но это не точно

    @GET("/products")
    fun getProducts(@Query("token") token:String,
                    @Query("id")id:Int):Call<Product>

    @PUT("/products")
    fun editProduct(@Query("token") token:String,
                    @Query("id")id:Int,
                    @Query("name") name: String,
                    @Query("price") price: Double,
                    @Query("description") description: String):Call<Product>

    @DELETE("/products")
    fun deleteProduct(@Query("token") token:String,
                      @Query("id") id: Int)
}