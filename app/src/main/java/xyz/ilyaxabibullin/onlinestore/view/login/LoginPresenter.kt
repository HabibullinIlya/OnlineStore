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

    override fun authorisation(login: String, password: String) {
        App.retrofit.create(UserApi::class.java).auth(login, password).enqueue(object : Callback<AuthResponse> {
            override fun onFailure(call: Call<AuthResponse>?, t: Throwable?) {
                t!!.printStackTrace()
                Log.d(TAG, "onFailure")
                view.showFailedNetworkMessage()
            }

            override fun onResponse(call: Call<AuthResponse>?, response: Response<AuthResponse>?) {


                    if (!response!!.body()!!.error) {
                        App.token = response.body()!!.token

                        Log.d(TAG,App.token)
                        App.id = response!!.body()!!
                                .user!!
                                .id

                        Log.d(TAG,App.id.toString())
                        Log.d(TAG, App.token)
                        view.navigateToMyShop()
                    } else {
                        Log.d(TAG, "not 1")

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