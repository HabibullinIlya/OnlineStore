package xyz.ilyaxabibullin.onlinestore.view.addproduct

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import xyz.ilyaxabibullin.onlinestore.App
import xyz.ilyaxabibullin.onlinestore.entitys.retrofit.Product
import xyz.ilyaxabibullin.onlinestore.entitys.retrofit.ProductResponse
import xyz.ilyaxabibullin.onlinestore.network.ProductApi

class AddProductPresenter(var view:AddProductContract.View):AddProductContract.Presenter{
    override fun buttonWasClicked(name: String,
                                  description: String,
                                  amount: Int,
                                  price: Double,
                                  category: String) {
        uploadDataFromNet(name,description,amount,price,category)
    }

    override fun uploadDataFromNet(name: String,
                            description: String,
                            amount: Int,
                            price: Double,
                            category: String) {

        App.retrofit.create(ProductApi::class.java).addProducts(App.token,
                name,
                description,
                amount,
                price,
                category).enqueue(object : Callback<ProductResponse>{

            override fun onFailure(call: Call<ProductResponse>?, t: Throwable?) {
                view.showFailedNetworkMessage()
            }

            override fun onResponse(call: Call<ProductResponse>?, response: Response<ProductResponse>?) {
                TODO("not implemented") //парсим json, кладем в бд, открыве
            }

        })



    }

}