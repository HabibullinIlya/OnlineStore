package xyz.ilyaxabibullin.onlinestore.entitys.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class CategoryResponse:Response(){
    @SerializedName("category")
    @Expose
    var category:Category? = null
}