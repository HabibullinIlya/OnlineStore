package xyz.ilyaxabibullin.onlinestore.view.splash

class SplashPresenter(var view: SplashContract.View) : SplashContract.Presenter {
    var model: SplashContract.Model = SplashModel()

    override fun activityWasStarted() {

        if (model.loadTokenFromDb() != null) {
            view.navigateToMyShop()
        }
        else{
            view.navigateToLogin()
        }
    }

}