package xyz.ilyaxabibullin.onlinestore.entitys.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AuthResponse{
    @SerializedName("access_token")
    @Expose
    var token: String = ""

    @SerializedName("error")
    var error: String = ""
}