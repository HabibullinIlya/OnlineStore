package xyz.ilyaxabibullin.onlinestore.base

import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import xyz.ilyaxabibullin.onlinestore.App

open abstract class BaseActivity: AppCompatActivity(),BaseView {
    override fun showFailedNetworkMessage(){
        //сделать через ресурсы
        var toast = Toast.makeText(this, "problems with internet",Toast.LENGTH_SHORT)
    }

    override fun onDestroy() {
        super.onDestroy()
        println("OnDestroy")
        App.checkRemember()
    }
}