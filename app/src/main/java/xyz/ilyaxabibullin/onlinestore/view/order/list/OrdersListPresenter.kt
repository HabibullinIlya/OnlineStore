package xyz.ilyaxabibullin.onlinestore.view.order.list

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import xyz.ilyaxabibullin.onlinestore.App
import xyz.ilyaxabibullin.onlinestore.entitys.retrofit.OrdersListResponse
import xyz.ilyaxabibullin.onlinestore.network.OrdersAPI

class OrdersListPresenter(var view: OrdersListContract.View): OrdersListContract.Presenter {

    private val TAG = javaClass.toString()
    override fun activityWasStarted() {
        App.retrofit.create(OrdersAPI::class.java).allOrders()
                .enqueue(object: Callback<OrdersListResponse>{
                    override fun onFailure(call: Call<OrdersListResponse>?, t: Throwable?) {
                        Log.d(TAG,"onFailure")
                        t!!.printStackTrace()
                    }

                    override fun onResponse(call: Call<OrdersListResponse>?, response: Response<OrdersListResponse>?) {
                        if(response!!.isSuccessful){
                            if (!response.body()!!.error){
                                view.showItem(response.body()!!
                                        .orders!!)
                            }
                            else{
                                Log.d(TAG,"no orders")
                            }
                        }
                        else{
                            Log.d(TAG,"not successful")
                        }
                    }

                })
    }
}