package xyz.ilyaxabibullin.onlinestore.entitys.retrofit

class Product{
    var name = ""
    var price = 0.0
    var description = ""
    var link = ""
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
}