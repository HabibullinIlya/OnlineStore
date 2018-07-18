package xyz.ilyaxabibullin.onlinestore.view.splash

import android.content.Intent
import android.os.Bundle
import xyz.ilyaxabibullin.onlinestore.R
import xyz.ilyaxabibullin.onlinestore.base.BaseActivity
import xyz.ilyaxabibullin.onlinestore.view.login.LoginActivity
import xyz.ilyaxabibullin.onlinestore.view.shop.ShopActivity

class SplashActivity : BaseActivity(),SplashContract.View {

    var presenter: SplashContract.Presenter = SplashPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme_NoActionBar)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        presenter.activityWasStarted()
    }
    override fun navigateToLogin() {
        var intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun navigateToMyShop() {
        var intent = Intent(this,ShopActivity::class.java)
        startActivity(intent)
        finish()
    }
}
