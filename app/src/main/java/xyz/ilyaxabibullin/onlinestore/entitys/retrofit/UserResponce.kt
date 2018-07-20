package xyz.ilyaxabibullin.onlinestore.entitys.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class UserResponce{
    @SerializedName("error")
    @Expose
    var error: Boolean? = null

    @SerializedName("user")
    @Expose
    var user: UserResponce? = null



}