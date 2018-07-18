package xyz.ilyaxabibullin.onlinestore.view.login

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import xyz.ilyaxabibullin.onlinestore.App
import xyz.ilyaxabibullin.onlinestore.entitys.retrofit.AuthResponse
import xyz.ilyaxabibullin.onlinestore.network.UserApi

class LoginPresenter : LoginContract.Presenter {
    val view: LoginContract.View

    constructor(_view: LoginContract.View) {
        view = _view
    }

    override fun authorisation(login:String, password:String) {
        loginBtnWasClicked()
        App.retrofit.create(UserApi::class.java).auth(login,password).enqueue(object: Callback<AuthResponse> {
            override fun onFailure(call: Call<AuthResponse>?, t: Throwable?) {
                view.showFailedNetworkMessage()
            }

            override fun onResponse(call: Call<AuthResponse>?, response: Response<AuthResponse>?) {
                var token = response!!.body()
                if(!token!!.error.equals("error")){
                    App.token = token.token
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