package xyz.ilyaxabibullin.onlinestore.entitys.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

class Order {
    @SerializedName("id")
    @Expose
    var id = 0

    @SerializedName("products")
    @Expose
    var products:ArrayList<Product>? = null

    @SerializedName("date")
    @Expose
    var date: String? = null

    @SerializedName("is_paid")
    @Expose
    var isPaid = false

    @SerializedName("is_done")
    @Expose
    var isDone = false

}



