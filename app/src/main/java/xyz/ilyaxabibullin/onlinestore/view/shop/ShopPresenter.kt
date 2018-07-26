package xyz.ilyaxabibullin.onlinestore.view.shop

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import xyz.ilyaxabibullin.onlinestore.App
import xyz.ilyaxabibullin.onlinestore.entitys.retrofit.ShopResponse
import xyz.ilyaxabibullin.onlinestore.network.ShopAPI

class ShopPresenter(var view: ShopContract.View):ShopContract.Presenter {
    private val TAG  = javaClass.toString()

    override fun activityWasStated(shop_id: Int) {
        App.retrofit.create(ShopAPI::class.java).getShop(shop_id)
                .enqueue(object: Callback<ShopResponse>{
                    override fun onFailure(call: Call<ShopResponse>?, t: Throwable?) {
                        t!!.printStackTrace()
                    }

                    override fun onResponse(call: Call<ShopResponse>?, response: Response<ShopResponse>?) {
                        if(response!!.isSuccessful){
                            if(!response.body()!!.error){
                                if(response.body()!!.shop!=null)
                                    view.showShop(response.body()!!.shop!!)
                            }else{
                                Log.d(TAG,response.message())
                            }

                        } else{
                            Log.d(TAG,"error network")
                        }
                    }
                })
    }
}