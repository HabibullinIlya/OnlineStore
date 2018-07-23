package xyz.ilyaxabibullin.onlinestore.network

import retrofit2.Call
import retrofit2.http.*
import xyz.ilyaxabibullin.onlinestore.entitys.retrofit.Product
import xyz.ilyaxabibullin.onlinestore.entitys.retrofit.ProductResponse
import xyz.ilyaxabibullin.onlinestore.entitys.retrofit.Response

interface ProductApi {
    @POST("/products")
    fun addProducts(@Query("access_token")token:String,
                    @Query("name") name: String,
                    @Query("description") description: String,
                    @Query("amount") amount:Int,
                    @Query("price") price: Double):                     Call<ProductResponse>
    //@Query("category")category: String

    @GET("/products")
    fun getProducts(@Query("access_token") token:String,
                    @Query("id")id:Int):Call<ProductResponse>

    @PUT("/products")
    fun editProduct(@Query("access_token") token:String,
                    @Query("id")id:Int,
                    @Query("name") name: String,
                    @Query("price") price: Double,
                    @Query("description") description: String):         Call<ProductResponse>

    @DELETE("/products")
    fun deleteProduct(@Query("access_token") token:String,
                      @Query("id") id: Int):Call<ProductResponse>

    @DELETE("/products/all?")
    fun searchProducts(@Query("name")name:String,
                       @Query("page")page:Int,
                       @Query("limit") limit:Int):Call<List<Product>>//нет респонса пока


}