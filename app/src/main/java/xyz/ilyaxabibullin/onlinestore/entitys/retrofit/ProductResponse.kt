package xyz.ilyaxabibullin.onlinestore.entitys.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ProductResponse{
    @SerializedName("error")
    @Expose
    var error = false
    @SerializedName("product")
    @Expose
    var product:Product? = null

}