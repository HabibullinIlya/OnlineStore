package xyz.ilyaxabibullin.onlinestore.base

import android.support.v7.app.AppCompatActivity
import android.widget.Toast

open abstract class BaseActivity: AppCompatActivity() {
    open fun showFailedNetworkMessage(){
        //сделать через ресурсы
        var toast = Toast.makeText(this, "problems with internet",Toast.LENGTH_SHORT)
    }
}