package xyz.ilyaxabibullin.onlinestore.view.login

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast
import xyz.ilyaxabibullin.onlinestore.R
import xyz.ilyaxabibullin.onlinestore.base.BaseActivity
import xyz.ilyaxabibullin.onlinestore.base.BasePresenter
import xyz.ilyaxabibullin.onlinestore.view.register.RegistrationActivity

class LoginActivity : BaseActivity(),LoginContract.View {

    var presenter:LoginContract.Presenter = LoginPresenter(this)

    private var registerBtn: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        registerBtn = findViewById(R.id.button_signup)
        registerBtn!!.setOnClickListener {
            var intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }
    }
    override fun showFailedMessage() {
        val toast = Toast.makeText(this,R.string.fail_login,Toast.LENGTH_SHORT)
    }

    override fun navigateTo() {
        val intent = Intent(this, RegistrationActivity::class.java)
        startActivity(intent)
    }



}
