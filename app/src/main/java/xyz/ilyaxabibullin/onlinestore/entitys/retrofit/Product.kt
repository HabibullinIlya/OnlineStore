package xyz.ilyaxabibullin.onlinestore.entitys.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Product{
    @SerializedName("id")
    @Expose
    var id = 0

    @SerializedName("name")
    @Expose
    var name = ""

    @SerializedName("price")
    @Expose
    var price = 0.0

    @SerializedName("description")
    @Expose
    var description = ""

    @SerializedName("shop")
    @Expose
    var shop: Shop? = null



    @SerializedName("amount")
    @Expose
    var number = 0

    constructor(){}
    constructor(name:String,price:Double){
        this.name = name
        this.price = price
    }


    companion object{
        fun createProducts(itemCount:Int):List<Product>{
            var products = ArrayList<Product>()
            for(i in 0 until 10){
                var product = Product("product ${itemCount +i}", 9999.0)
                products.add(product)


            }
            return products
        }
    }

    override fun toString(): String {
        return "[\"id\":$id, \"name\" : \"$name\", \"price\": \"$price\" "
    }
}