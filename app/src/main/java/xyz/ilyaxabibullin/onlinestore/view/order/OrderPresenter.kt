package xyz.ilyaxabibullin.onlinestore.view.order

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import xyz.ilyaxabibullin.onlinestore.App
import xyz.ilyaxabibullin.onlinestore.entitys.retrofit.OrderResponse
import xyz.ilyaxabibullin.onlinestore.network.OrdersAPI

class OrderPresenter(var view: OrderContract.View) : OrderContract.Presenter {
    private val TAG = javaClass.toString()
    override fun activityWasStarted(id: Int) {
        App.retrofit.create(OrdersAPI::class.java).getOrder()
                .enqueue(object : Callback<OrderResponse> {
                    override fun onFailure(call: Call<OrderResponse>?, t: Throwable?) {
                        t!!.printStackTrace()
                        Log.d(TAG, "onFailure()")
                    }

                    override fun onResponse(call: Call<OrderResponse>?, response: Response<OrderResponse>?) {
                        if (response!!.isSuccessful) {
                            if (!response!!.body()!!.error) {
                                val order = response.body()!!.order!!
                                view.showOrder(order)
                                view.showProducts(order.products!!)
                            } else {
                                Log.d(TAG,"errror")
                            }
                        } else {
                            Log.d(TAG, "error request")
                        }
                    }


                })
    }
}