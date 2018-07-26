package xyz.ilyaxabibullin.onlinestore.view.order

import android.util.Log
import retrofit2.Call
import retrofit2.Callback

import xyz.ilyaxabibullin.onlinestore.App
import xyz.ilyaxabibullin.onlinestore.entitys.retrofit.OrderResponse
import xyz.ilyaxabibullin.onlinestore.entitys.retrofit.Response
import xyz.ilyaxabibullin.onlinestore.network.OrdersAPI
import xyz.ilyaxabibullin.onlinestore.network.PaymentsApi

class OrderPresenter(var view: OrderContract.View) : OrderContract.Presenter {
    override fun payBtnWasClicked(id: Int) {
        App.retrofit.create(PaymentsApi::class.java).payments(id = id)
                .enqueue(object : Callback<Response> {
                    override fun onFailure(call: Call<Response>?, t: Throwable?) {
                        t!!.printStackTrace()
                    }

                    override fun onResponse(call: Call<Response>?, response: retrofit2.Response<Response>?) {
                        if (response!!.isSuccessful) {
                            if (!response.body()!!.error) {
                                view.message("order successful payed")
                                view.update()
                            } else {
                                view.message(response.body()!!.message)

                            }
                        } else {
                            view.message("problem with network")
                        }
                    }

                })

    }

    private val TAG = javaClass.toString()
    override fun activityWasStarted(id: Int) {
        App.retrofit.create(OrdersAPI::class.java).getOrder()
                .enqueue(object : Callback<OrderResponse> {
                    override fun onFailure(call: Call<OrderResponse>?, t: Throwable?) {
                        t!!.printStackTrace()
                        Log.d(TAG, "onFailure()")
                    }

                    override fun onResponse(call: Call<OrderResponse>?, response: retrofit2.Response<OrderResponse>?) {
                        if (response!!.isSuccessful) {
                            if (!response!!.body()!!.error) {
                                val order = response.body()!!.order!!
                                view.showOrder(order)
                                view.showProducts(order.products!!)
                            } else {
                                Log.d(TAG, "errror")
                            }
                        } else {
                            Log.d(TAG, "error request")
                        }
                    }


                })
    }
}