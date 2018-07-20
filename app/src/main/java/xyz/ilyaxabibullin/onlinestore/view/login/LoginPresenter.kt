package xyz.ilyaxabibullin.onlinestore.view.login

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

    constructor(_view: LoginContract.View) {
        view = _view
    }

    override fun authorisation(login:String, password:String) {
        App.retrofit.create(UserApi::class.java).auth(login,password).enqueue(object: Callback<AuthResponse> {
            override fun onFailure(call: Call<AuthResponse>?, t: Throwable?) {
                view.showFailedNetworkMessage()
            }

            override fun onResponse(call: Call<AuthResponse>?, response: Response<AuthResponse>?) {
                var token = response!!.body()
                println("tut")
                println(token.toString())
                if(!token!!.error.equals("error")){
                    App.token = token.token
                    Log.d(TAG,App.token)
                    view.navigateToMyShop()

                }else{
                    view.showFailedMessage()
                }

            }

        })
    }
    override fun regBtnWasClicked(){
        view.navigateToRegActivity()
    }

    override fun loginBtnWasClicked() {
        view.navigateToMyShop()
    }



}