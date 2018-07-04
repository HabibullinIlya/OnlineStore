package xyz.ilyaxabibullin.onlinestore

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button

class LoginActivity : AppCompatActivity() {
    private var registerBtn: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        registerBtn = findViewById(R.id.button_signup)
        registerBtn!!.setOnClickListener{
            var intent = Intent(this,RegistrationActivity::class.java)
            startActivity(intent)
        }

    }
}
