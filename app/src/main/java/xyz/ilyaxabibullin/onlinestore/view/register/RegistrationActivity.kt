package xyz.ilyaxabibullin.onlinestore.view.register

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import xyz.ilyaxabibullin.onlinestore.R
import xyz.ilyaxabibullin.onlinestore.base.BaseActivity

class RegistrationActivity: BaseActivity(),RegistrationContract.View {
    override fun setPresenter(presenter: RegistrationContract.Presenter) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showFailMessage() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun navigate() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
    }
}