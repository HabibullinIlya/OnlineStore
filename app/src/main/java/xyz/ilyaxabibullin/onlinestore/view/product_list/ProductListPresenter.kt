package xyz.ilyaxabibullin.onlinestore.view.product_list

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import xyz.ilyaxabibullin.onlinestore.App
import xyz.ilyaxabibullin.onlinestore.entitys.retrofit.Product
import xyz.ilyaxabibullin.onlinestore.entitys.retrofit.ProductResponse
import xyz.ilyaxabibullin.onlinestore.entitys.retrofit.ProductsListResponse
import xyz.ilyaxabibullin.onlinestore.entitys.retrofit.ShopResponse
import xyz.ilyaxabibullin.onlinestore.network.ProductApi
import xyz.ilyaxabibullin.onlinestore.network.ShopAPI

class ProductListPresenter(var view: ProductListContract.View) : ProductListContract.Presenter {
    private val TAG = javaClass.toString()


    override fun activityStarted(action: Int) {
        when (action) {
            ProductAction.SHOP_PRODUCTS.Action -> {
                loadShopProducts()
            }
            ProductAction.DEFAULT.Action -> {
                defaultLoadProducts()
            }

        }
    }

    private fun loadShopProducts() {

        var items = ArrayList<Product>()

        if (App.id == view.getShopOwnerId()) {
            view.setItMyProducts(true)
        } else {
            view.setItMyProducts(false)
        }

        App.retrofit.create(ShopAPI::class.java).getShop(view.getShopOwnerId())
                .enqueue(object : Callback<ShopResponse> {
                    override fun onFailure(call: Call<ShopResponse>?, t: Throwable?) {
                        t!!.printStackTrace()
                    }

                    override fun onResponse(call: Call<ShopResponse>?, response: Response<ShopResponse>?) {
                        if (response!!.isSuccessful) {
                            if (!response.body()!!.error) {
                                if(response.body()!!.shop!!.products!=null){
                                    view.showItems(response.body()!!.shop!!.products!!)
                                }else{
                                    Log.d(TAG,"no products")
                                }
                            } else {

                            }
                        } else {

                        }
                    }

                })
        view.showItems(items)
    }


    override fun queryWasChanged(searchQuery: String) {
        searchProducts(searchQuery);
    }

    override fun defaultLoadProducts() {//it maybe should be in model, but i am stupid man and it will here
        var memoryRandom = Array<Int>(10, { 0 })
        var r = Math.random() * 1
        var i = 0
        while (i < 10) {
            /*if(elExist(memoryRandom,r as Int)){
                memoryRandom[i] = r
                i++
            }*/
            memoryRandom[i] = 1
            i++
        }
        var resultSet: ArrayList<Product> = ArrayList()
        memoryRandom.forEach {
            App.retrofit.create(ProductApi::class.java).getProducts(App.token, memoryRandom[it])
                    .enqueue(object : Callback<ProductResponse> {
                        override fun onFailure(call: Call<ProductResponse>?, t: Throwable?) {
                            t!!.printStackTrace()
                        }

                        override fun onResponse(call: Call<ProductResponse>?, response: Response<ProductResponse>?) {
                            if (response!!.isSuccessful) {
                                val product: Product = response!!
                                        .body()!!
                                        .product!!

                                Log.d(TAG, "id = ${product.id}")
                                Log.d(TAG, resultSet.toString())
                                resultSet.add(product)
                                view.showItems(resultSet)
                            }


                        }
                    })
        }
        Log.d(TAG, resultSet.toString())

    }

    private fun elExist(ar: Array<Int>, el: Int): Boolean {
        for (i in 0 until ar.size) {
            if (el == ar[i]) {
                return true;
            }
        }
        return false;
    }

    override fun searchProducts(searchQuery: String) {//it maybe should be in model, but i am stupid man and it will here
        App.retrofit.create(ProductApi::class.java)
                .searchProducts(searchQuery, 1, 15)
                .enqueue(object : Callback<ProductsListResponse> {
                    override fun onFailure(call: Call<ProductsListResponse>?, t: Throwable?) {
                        Log.d(TAG, "onFailure searchProducts")
                        t!!.printStackTrace()
                    }

                    override fun onResponse(call: Call<ProductsListResponse>?, response: Response<ProductsListResponse>?) {
                        if (response!!.isSuccessful) {
                            if (!response!!
                                            .body()!!
                                            .error) {
                                var resultSet = response.body()!!.products
                                view.showItems(resultSet as ArrayList<Product>)
                                Log.d(TAG, resultSet[1].name)
                            } else {
                                Log.d(TAG, "else")
                            }
                        } else {
                            var product = Product()
                            var resultSet = ArrayList<Product>()
                            view.showItems(resultSet)
                        }
                    }

                })
    }

    override fun scrolledToEnd() {
        //TODO("not implemented") есть АПИ но я мудак никак не доберусь до этого
    }
}