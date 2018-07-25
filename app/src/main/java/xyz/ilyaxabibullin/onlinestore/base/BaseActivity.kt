package xyz.ilyaxabibullin.onlinestore.base

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import xyz.ilyaxabibullin.onlinestore.App

open abstract class BaseActivity: AppCompatActivity(),BaseView {

    override fun onDestroy() {
        super.onDestroy()
        println("OnDestroy")
        App.checkRemember()
    }
}