package xyz.ilyaxabibullin.onlinestore.entitys.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class UserResponse{
    @SerializedName("error")
    @Expose
    var error: Boolean? = null

    @SerializedName("user")
    @Expose
    var user: User? = null



}