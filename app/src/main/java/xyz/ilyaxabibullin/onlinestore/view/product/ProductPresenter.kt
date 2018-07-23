package xyz.ilyaxabibullin.onlinestore.view.product

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import xyz.ilyaxabibullin.onlinestore.App
import xyz.ilyaxabibullin.onlinestore.entitys.retrofit.ProductResponse
import xyz.ilyaxabibullin.onlinestore.network.ProductApi

class ProductPresenter(var view:ProductContract.View): ProductContract.Presenter{
    override fun activityWasStarted(id: Int) {
        println("id = $id")

        App.retrofit
                .create(ProductApi::class.java)
                .getProducts(App.token,id)
                .enqueue(object: Callback<ProductResponse>{
                    override fun onFailure(call: Call<ProductResponse>?, t: Throwable?) {
                        t!!.printStackTrace()
                    }

                    override fun onResponse(call: Call<ProductResponse>?, response: Response<ProductResponse>?) {
                        var product = response!!
                                .body()!!
                                .product!!
                        view.showProduct(product.name,
                                product.description,
                                product.number,
                                product.price)
                    }

                })
    }

    override fun btnBuyWasClicked(id: Int) {
        view.navigateToOrder()
    }

    override fun btnToCartWasClicked() {


    }

    override fun btnToShopWasClicked(id: Int) {
        view.navigateToShop()
    }
}