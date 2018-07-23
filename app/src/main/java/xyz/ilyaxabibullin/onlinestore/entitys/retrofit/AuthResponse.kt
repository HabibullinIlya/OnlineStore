package xyz.ilyaxabibullin.onlinestore.entitys.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AuthResponse:Response(){
    @SerializedName("access_token")
    @Expose
    var token = ""


    @SerializedName("user")
    @Expose
    var user: User? = null

}