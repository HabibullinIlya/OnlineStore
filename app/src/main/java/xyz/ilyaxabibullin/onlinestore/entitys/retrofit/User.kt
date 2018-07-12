package xyz.ilyaxabibullin.onlinestore.entitys.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class User{
    @SerializedName("id")
    @Expose
    private var id: Int? = null

    @SerializedName("username")
    @Expose
    private var username: String? = null

    @SerializedName("first_name")
    @Expose
    private var firstName: String? = null

    @SerializedName("last_name")
    @Expose
    private var lastName: String? = null

    @SerializedName("email")
    @Expose
    private var email: String? = null

    @SerializedName("roles")
    @Expose
    private var roles: List<String>? = null
}