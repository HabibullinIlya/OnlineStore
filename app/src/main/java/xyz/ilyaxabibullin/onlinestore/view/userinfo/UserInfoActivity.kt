package xyz.ilyaxabibullin.onlinestore.view.userinfo

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_profile_info.*
import xyz.ilyaxabibullin.onlinestore.App
import xyz.ilyaxabibullin.onlinestore.R
import xyz.ilyaxabibullin.onlinestore.base.BaseActivity
import xyz.ilyaxabibullin.onlinestore.entitys.retrofit.User
import xyz.ilyaxabibullin.onlinestore.view.shop.ShopActivity
import xyz.ilyaxabibullin.onlinestore.view.shop.create_shop.CreateShopActivity

class UserInfoActivity : BaseActivity(), UserInfoContract.View {


    private lateinit var mActionBarToolBar: Toolbar

    private var presenter: UserInfoContract.Presenter = UserInfoPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_info)

        mActionBarToolBar = findViewById(R.id.toolbar_actionbar)
        setSupportActionBar(mActionBarToolBar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true);

        presenter.activityWasStarted(App.id ?: 0)
        create_shop.setOnClickListener {
            presenter.toCreateShopBtnWasClicked()
        }
        to_shop_btn.setOnClickListener {
            presenter.toShopBtnWasClicked(App.id!!)
        }


    }

    override fun showInfo(user: User) {
        user_name_info.text = user.firstName + " " + user.lastName
        email_info.text = user.email
        this.shop.text = user.email
    }

    override fun navigateToShop() {
        val intent = Intent(this, ShopActivity::class.java)
        intent.putExtra("shop_id", App.id.toString())
        startActivity(intent)
    }

    override fun navigateToCreateShop() {
        val intent = Intent(this, CreateShopActivity::class.java)
        startActivity(intent)
    }


    override fun hideToShopBtn() {
        create_shop.visibility = View.VISIBLE
        to_shop_btn.visibility = View.GONE
    }

    override fun hideCreateShopBtn() {
        create_shop.visibility = View.GONE
        to_shop_btn.visibility = View.VISIBLE
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item!!.itemId) {
            android.R.id.home -> {
                this.finish()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}