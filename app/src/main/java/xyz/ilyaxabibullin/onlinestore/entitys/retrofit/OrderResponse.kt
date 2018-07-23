package xyz.ilyaxabibullin.onlinestore.entitys.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class OrderResponse:Response(){
    @SerializedName("order")
    @Expose
    var order: Order? = null
}