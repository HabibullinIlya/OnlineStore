package xyz.ilyaxabibullin.onlinestore.view.shop.create_shop

import android.provider.Settings.Global.getString
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import xyz.ilyaxabibullin.onlinestore.App
import xyz.ilyaxabibullin.onlinestore.R
import xyz.ilyaxabibullin.onlinestore.entitys.retrofit.Shop
import xyz.ilyaxabibullin.onlinestore.entitys.retrofit.ShopResponse
import xyz.ilyaxabibullin.onlinestore.network.ShopAPI

class CreateShopPresenter(var view: CreateShopContract.View):CreateShopContract.Presenter{
    override fun uploadInfo(shop: Shop) {
        App.retrofit.create(ShopAPI::class.java).addShop(name = shop.name,description = shop.description)
                .enqueue(object: Callback<ShopResponse> {
                    override fun onFailure(call: Call<ShopResponse>?, t: Throwable?) {
                        t!!.printStackTrace()
                    }

                    override fun onResponse(call: Call<ShopResponse>?, response: Response<ShopResponse>?) {
                        if(response!!.isSuccessful){
                            if(!response.body()!!.error){
                                view.message("shop successful created")
                                view.navigateToShop(response.body()!!.shop!!.id)
                            }
                        }else{
                            view.message("problem with network")

                        }
                    }

                })
    }

}