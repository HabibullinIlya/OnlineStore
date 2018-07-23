package xyz.ilyaxabibullin.onlinestore.entitys.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ProductsListResponse:Response() {
    @SerializedName("products")
    @Expose
    var products: List<Product>? = null

    @SerializedName("total")
    @Expose
    var total = 0
}