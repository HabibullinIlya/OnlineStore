package xyz.ilyaxabibullin.onlinestore.entitys.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CartResponse:Response() {
    @SerializedName("cart")
    @Expose
    var cart: Cart? = null

}
