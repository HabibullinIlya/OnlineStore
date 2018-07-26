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
    var name = ""

    @SerializedName("description")
    @Expose
    var description = ""

    @SerializedName("owner")
    @Expose
    var owner:User = User()


    @SerializedName("open_date")
    @Expose
    var date:String = ""

    @SerializedName("products")
    @Expose
    var products:ArrayList<Product>? = null

}