package xyz.ilyaxabibullin.onlinestore.view.product

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import xyz.ilyaxabibullin.onlinestore.App
import xyz.ilyaxabibullin.onlinestore.entitys.retrofit.CartResponse
import xyz.ilyaxabibullin.onlinestore.entitys.retrofit.ProductResponse
import xyz.ilyaxabibullin.onlinestore.network.CartsApi
import xyz.ilyaxabibullin.onlinestore.network.ProductApi

class ProductPresenter(var view: ProductContract.View) : ProductContract.Presenter {
    private val TAG = "ProductPresenter"
    override fun activityWasStarted(id: Int) {
        println("id = $id")
        Log.d(TAG, "access token ${App.token}")
        App.retrofit
                .create(ProductApi::class.java)
                .getProducts(token = App.token, id = id)
                .enqueue(object : Callback<ProductResponse> {
                    override fun onFailure(call: Call<ProductResponse>?, t: Throwable?) {
                        t!!.printStackTrace()
                    }

                    override fun onResponse(call: Call<ProductResponse>?, response: Response<ProductResponse>?) {
                        var product = response!!
                                .body()!!
                                .product!!
                        view.showProduct(product)
                    }

                })
    }



    override fun btnToCartWasClicked(id: Int) {
        App.retrofit.create(CartsApi::class.java).addToCart(id = id)
                .enqueue(object : Callback<CartResponse> {
                    override fun onFailure(call: Call<CartResponse>?, t: Throwable?) {
                        t!!.printStackTrace()
                        Log.d(TAG,"onFailure")
                    }

                    override fun onResponse(call: Call<CartResponse>?, response: Response<CartResponse>?) {
                        if(response!!.isSuccessful){
                            if(!response.body()!!.error){
                                view.successMessage()
                            }else{
                                view.errorMessage("error")
                            }

                        }else{
                            view.errorMessage("problem with network")
                        }
                    }
                })
    }

    override fun btnToShopWasClicked(id: Int) {
        view.navigateToShop()
    }
}