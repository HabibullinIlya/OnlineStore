package xyz.ilyaxabibullin.onlinestore.entitys.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class User{
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("username")
    @Expose
    var username: String? = null

    @SerializedName("first_name")
    @Expose
    var firstName: String? = null

    @SerializedName("last_name")
    @Expose
    var lastName: String? = null

    @SerializedName("email")
    @Expose
    var email: String? = null

    @SerializedName("roles")
    @Expose
    var roles: List<String>? = null


    @SerializedName("is_shop_owner")
    @Expose
    var isShopOwner = false
}