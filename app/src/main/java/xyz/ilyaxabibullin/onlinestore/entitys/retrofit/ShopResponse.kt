package xyz.ilyaxabibullin.onlinestore.entitys.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ShopResponse:Response() {
    @SerializedName("shop")
    @Expose
    var shop: Shop? = null
}