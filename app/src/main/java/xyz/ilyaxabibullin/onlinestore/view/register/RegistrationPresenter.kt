package xyz.ilyaxabibullin.onlinestore.view.register

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import xyz.ilyaxabibullin.onlinestore.App
import xyz.ilyaxabibullin.onlinestore.entitys.retrofit.UserResponse
import xyz.ilyaxabibullin.onlinestore.network.RegisterApi


class RegistrationPresenter:RegistrationContract.Presenter {

    val view: RegistrationContract.View

    constructor(_View:RegistrationContract.View){
        view = _View
    }

    override fun register(email: String, fName: String, lName: String, pass: String) {
        /*App.retrofit.create(RegisterApi::class.java).register(email, fName,lName,pass)
                .enqueue(object: Callback<UserResponse> {
                    override fun onFailure(call: Call<UserResponse>?, t: Throwable?) {
                        t!!.printStackTrace()
                    }

                    override fun onResponse(call: Call<UserResponse>?, response: Response<UserResponse>?) {
                        if(response!!
                                        .body()
                                        !!.error!!){
                            view.navigate()
                        }
                        else{

                        }
                    }

                })*/
        view.navigate()
    }
}