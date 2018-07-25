package xyz.ilyaxabibullin.onlinestore.entitys.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class OrdersListResponse:Response() {

    @SerializedName("orders")
    @Expose
    var orders: List<Order>? = null

    @SerializedName("total")
    @Expose
    var total = 0

}