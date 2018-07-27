package xyz.ilyaxabibullin.onlinestore.view.splash

import android.util.Log
import xyz.ilyaxabibullin.onlinestore.App

class SplashPresenter(var view: SplashContract.View) : SplashContract.Presenter {
    var model: SplashContract.Model = SplashModel()

    override fun activityWasStarted() {
        Log.d("SPLASH",model.loadTokenFromDb().toString())
        if (model.loadTokenFromDb() != null) {
            App.token = model.loadTokenFromDb()!!.token
            view.navigateToProductList()
        }
        else{
            view.navigateToLogin()
        }
    }

}