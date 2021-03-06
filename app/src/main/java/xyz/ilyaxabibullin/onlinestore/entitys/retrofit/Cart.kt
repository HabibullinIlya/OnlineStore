package xyz.ilyaxabibullin.onlinestore.entitys.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import xyz.ilyaxabibullin.onlinestore.base.BaseActivity

class Cart {
    @SerializedName("id")
    @Expose

    var id = 0
    @SerializedName("user")
    @Expose
    var user:User? = null

    @SerializedName("products")
    @Expose
    var products:ArrayList<Product>? = null
}
