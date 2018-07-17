package xyz.ilyaxabibullin.onlinestore.view.product_list

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import xyz.ilyaxabibullin.onlinestore.App
import xyz.ilyaxabibullin.onlinestore.App.token
import xyz.ilyaxabibullin.onlinestore.entitys.retrofit.Product
import xyz.ilyaxabibullin.onlinestore.network.ProductApi

class ProductListPresenter(view: ProductListContract.View):ProductListContract.Presenter{



    override fun queryWasChanged(searchQuery: String) {
        searchProducts(searchQuery);
    }

    override fun defaultLoadProducts(): List<Product> {//it maybe should be in model, but i am stupid man and it will here
        var memoryRandom = Array<Int>(10,{0})
        var r = Math.random()*100
        var i = 0
        while(i<10){
            if(elExist(memoryRandom,r as Int)){
                memoryRandom[i] = r
                i++
            }
        }
        var resultSet: ArrayList<Product> = ArrayList()
        memoryRandom.forEach {
            App.retrofit.create(ProductApi::class.java).getProducts(App.token,memoryRandom[it])
                    .enqueue(object : Callback<Product> {
                        override fun onFailure(call: Call<Product>?, t: Throwable?) {
                            TODO("not implemented") //cкорее всего ошибка сети
                        }

                        override fun onResponse(call: Call<Product>?, response: Response<Product>?) {
                            TODO("not implemented") //добавить полученый респонс в аррай выдачи
                        }
                    })
        }

        return resultSet

    }
    private fun elExist(ar: Array<Int>, el: Int):Boolean{
        for(i in 0 until ar.size ){
            if(el == ar[i]){
                return true;
            }
        }
        return false;
    }

    override fun searchProducts(searchQuery: String): List<Product> {//it maybe should be in model, but i am stupid man and it will here
        TODO("not implemented") //изучить api для поиска
    }

}