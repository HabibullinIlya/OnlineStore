package xyz.ilyaxabibullin.onlinestore.view.register

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_registration.*
import xyz.ilyaxabibullin.onlinestore.R
import xyz.ilyaxabibullin.onlinestore.base.BaseActivity

class RegistrationActivity: BaseActivity(),RegistrationContract.View {

    private var presenter: RegistrationContract.Presenter = RegistrationPresenter(this)
    private var email = ""
    private var firstName = ""
    private var lastName = ""
    private var pass = ""
    private var passAgain = ""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        failed_register_data.visibility = View.GONE


        registration_btn.setOnClickListener{
            email = enter_email.text.toString()
            firstName = enter_user_name.text.toString()
            lastName = enter_user_lname.text.toString()
            pass = enter_pass.text.toString()
            passAgain = enter_pass_again.text.toString()
            if(pass.length<8){
                failed_register_data.text = R.string.fail_pass_small.toString()
                failed_register_data.visibility = View.VISIBLE
            }
            if (pass == passAgain){
                //вызываем метод из презентора

            }else{
                failed_register_data.visibility = View.VISIBLE
            }

        }
    }

    override fun showFailMessage() {

    }

    override fun navigate() {

    }
}