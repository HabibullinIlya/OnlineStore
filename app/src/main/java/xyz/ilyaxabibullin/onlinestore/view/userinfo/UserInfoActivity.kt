package xyz.ilyaxabibullin.onlinestore.view.userinfo

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_profile_info.*
import xyz.ilyaxabibullin.onlinestore.App
import xyz.ilyaxabibullin.onlinestore.R
import xyz.ilyaxabibullin.onlinestore.base.BaseActivity
import xyz.ilyaxabibullin.onlinestore.entitys.retrofit.User

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

        presenter.activityWasStarted(App.id?:0)




    }

    override fun showInfo(user: User) {
        user_name_info.text = user.firstName + " " + user.lastName
        email_info.text = user.email
        this.shop.text = user.email
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