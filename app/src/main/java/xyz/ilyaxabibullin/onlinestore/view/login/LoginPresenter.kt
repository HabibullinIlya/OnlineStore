package xyz.ilyaxabibullin.onlinestore.view.login

import android.media.session.MediaSession
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import xyz.ilyaxabibullin.onlinestore.App
import xyz.ilyaxabibullin.onlinestore.entitys.Token
import xyz.ilyaxabibullin.onlinestore.network.UserApi

class LoginPresenter : LoginContract.Presenter {
    val view: LoginContract.View

    constructor(_view: LoginContract.View) {
        view = _view
    }

    override fun authtorisation(login:String,password:String) {
        App.retrofit.create(UserApi::class.java).auth(login,password).enqueue(object: Callback<Token> {
            override fun onFailure(call: Call<Token>?, t: Throwable?) {
                view.showFailedNetworkMessage()
            }

            override fun onResponse(call: Call<Token>?, response: Response<Token>?) {

            }

        })
    }

}