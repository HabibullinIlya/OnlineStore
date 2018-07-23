package xyz.ilyaxabibullin.onlinestore.entitys.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*
import kotlin.collections.ArrayList

class Shop {
    @SerializedName("id")
    @Expose
    var id = 0


    @SerializedName("name")
    @Expose
    var name = 0

    @SerializedName("description")
    @Expose
    var description = ""

    @SerializedName("owner")
    @Expose
    var owner:User = User()


    @SerializedName("date")
    @Expose
    var date:Date = Date()

    @SerializedName("products")
    @Expose
    var products:ArrayList<Product>? = null

}