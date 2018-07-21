package xyz.ilyaxabibullin.onlinestore.view.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import xyz.ilyaxabibullin.onlinestore.App
import xyz.ilyaxabibullin.onlinestore.R
import xyz.ilyaxabibullin.onlinestore.base.BaseActivity
import xyz.ilyaxabibullin.onlinestore.view.product_list.ProductAction
import xyz.ilyaxabibullin.onlinestore.view.product_list.ProductListActivity

import xyz.ilyaxabibullin.onlinestore.view.register.RegistrationActivity
import xyz.ilyaxabibullin.onlinestore.view.shop.create_shop.CreateShopActivity

class LoginActivity : BaseActivity(),LoginContract.View {

    private var presenter:LoginContract.Presenter = LoginPresenter(this)

    private lateinit var registerBtn: Button
    private lateinit var loginBtn: Button
    private lateinit var emailText: EditText
    private lateinit var passText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        emailText = findViewById(R.id.login_field)
        passText = findViewById(R.id.password_field)

        registerBtn = findViewById(R.id.button_signup)
        registerBtn.setOnClickListener {
            presenter.regBtnWasClicked()
        }
        loginBtn = findViewById(R.id.button_login)
        loginBtn.setOnClickListener{
            App.remember = checkBox.isChecked
            presenter.authorisation(emailText.text.toString(),passText.text.toString())
        }
    }
    override fun showFailedMessage() {
        val toast = Toast.makeText(this,R.string.fail_login,Toast.LENGTH_SHORT)
    }

    override fun navigateToRegActivity() {
        val intent = Intent(this, RegistrationActivity::class.java)
        startActivity(intent)

    }

    override fun navigateToMyShop() {
        val intent = Intent(this, ProductListActivity::class.java)
        intent.putExtra("action",ProductAction.DEFAULT.Action.toString())
        startActivity(intent)
        finish()
    }




}
