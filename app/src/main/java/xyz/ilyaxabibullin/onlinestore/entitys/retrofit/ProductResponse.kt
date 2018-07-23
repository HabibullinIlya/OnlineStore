package xyz.ilyaxabibullin.onlinestore.entitys.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ProductResponse:Response(){

    @SerializedName("product")
    @Expose
    var product:Product? = null

}