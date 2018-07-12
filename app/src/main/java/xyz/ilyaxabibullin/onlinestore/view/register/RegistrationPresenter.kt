package xyz.ilyaxabibullin.onlinestore.view.register

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import xyz.ilyaxabibullin.onlinestore.App
import xyz.ilyaxabibullin.onlinestore.entitys.retrofit.RespUser
import xyz.ilyaxabibullin.onlinestore.network.RegisterApi


class RegistrationPresenter:RegistrationContract.Presenter {

    val view: RegistrationContract.View

    constructor(_View:RegistrationContract.View){
        view = _View
    }

    override fun register(email: String, fName: String, lName: String, pass: String) {
        App.retrofit.create(RegisterApi::class.java).register(email, fName,lName,pass)
                .enqueue(object: Callback<RespUser> {
                    override fun onFailure(call: Call<RespUser>?, t: Throwable?) {
                        view.showFailMessage()
                    }

                    override fun onResponse(call: Call<RespUser>?, response: Response<RespUser>?) {

                    }

                })
    }
}