package xyz.ilyaxabibullin.onlinestore.view.login

import android.content.Context
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import xyz.ilyaxabibullin.onlinestore.App
import xyz.ilyaxabibullin.onlinestore.entitys.retrofit.AuthResponse
import xyz.ilyaxabibullin.onlinestore.network.UserApi

class LoginPresenter : LoginContract.Presenter {

    val TAG = "LoginPresenter"

    val view: LoginContract.View
    val model: LoginContract.Model = LoginModel()
    val context: Context


    constructor(view: LoginContract.View,context:Context) {
        this.view = view
        this.context = context

    }

    override fun authorisation(login: String, password: String) {
        App.retrofit.create(UserApi::class.java).auth(login, password).enqueue(object : Callback<AuthResponse> {
            override fun onFailure(call: Call<AuthResponse>?, t: Throwable?) {
                t!!.printStackTrace()
            }

            override fun onResponse(call: Call<AuthResponse>?, response: Response<AuthResponse>?) {
                if(response!!.isSuccessful) {
                    if (!response!!.body()!!.error) {
                        App.token = response.body()!!.token

                        Log.d(TAG, App.token)
                        App.id = response!!.body()!!
                                .user!!
                                .id

                        Log.d(TAG, App.id.toString())
                        Log.d(TAG, App.token)
                        model.saveTokenToDB(App.token)
                        view.navigateToMyShop()

                    } else {
                        Log.d(TAG, "logining error")
                        //TODO("")//сделать через ресурсы
                        view.showErrorMessage(context,"logining error")
                    }
                }
                else{
                    Log.d(TAG, "request error ")
                    //TODO("")//сделать через ресурсы
                    view.showErrorMessage(context, "request error")
                }
            }

        })
    }

    override fun regBtnWasClicked() {
        view.navigateToRegActivity()
    }

    override fun loginBtnWasClicked() {
        view.navigateToMyShop()
    }


}