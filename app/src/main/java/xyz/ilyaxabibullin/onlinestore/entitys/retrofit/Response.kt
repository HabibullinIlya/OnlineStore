package xyz.ilyaxabibullin.onlinestore.entitys.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class Response{
    @SerializedName("error")
    @Expose
    var error = false
}