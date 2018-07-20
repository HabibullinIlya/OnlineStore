package xyz.ilyaxabibullin.onlinestore.view.product_list

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import xyz.ilyaxabibullin.onlinestore.App
import xyz.ilyaxabibullin.onlinestore.entitys.retrofit.Product
import xyz.ilyaxabibullin.onlinestore.entitys.retrofit.ProductResponse
import xyz.ilyaxabibullin.onlinestore.network.ProductApi

class ProductListPresenter(var view: ProductListContract.View):ProductListContract.Presenter{


    val TAG = "ProductListPresenter"

    override fun queryWasChanged(searchQuery: String) {
        searchProducts(searchQuery);
    }

    override fun defaultLoadProducts() {//it maybe should be in model, but i am stupid man and it will here
        var memoryRandom = Array<Int>(10,{0})
        var r = Math.random()*1
        var i = 0
        while(i<10){
            /*if(elExist(memoryRandom,r as Int)){
                memoryRandom[i] = r
                i++
            }*/
            memoryRandom[i] = 1
            i++
        }
        var resultSet: ArrayList<Product> = ArrayList()
        memoryRandom.forEach {
            App.retrofit.create(ProductApi::class.java).getProducts(App.token,memoryRandom[it])
                    .enqueue(object : Callback<ProductResponse> {
                        override fun onFailure(call: Call<ProductResponse>?, t: Throwable?) {
                            t!!.printStackTrace()
                        }

                        override fun onResponse(call: Call<ProductResponse>?, response: Response<ProductResponse>?) {
                            var product:Product = response!!
                                    .body()!!
                                    .product!!
                            Log.d(TAG,"price = ${product.price}")
                            Log.d(TAG,resultSet.toString())
                            resultSet.add(product)
                            view.showItems(resultSet)

                        }
                    })
        }
        Log.d(TAG,resultSet.toString())

    }
    private fun elExist(ar: Array<Int>, el: Int):Boolean{
        for(i in 0 until ar.size ){
            if(el == ar[i]){
                return true;
            }
        }
        return false;
    }

    override fun searchProducts(searchQuery: String){//it maybe should be in model, but i am stupid man and it will here
        TODO("not implemented") //изучить api для поиска
    }

    override fun scrolledToEnd() {
        //TODO("not implemented") подгрузить новую страницу. пока еще нет API
    }
}