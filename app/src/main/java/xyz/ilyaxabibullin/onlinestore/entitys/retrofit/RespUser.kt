package xyz.ilyaxabibullin.onlinestore.entitys.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class RespUser{
    @SerializedName("error")
    @Expose
    var error: Boolean? = null

    @SerializedName("user")
    @Expose
    var user: RespUser? = null



}