package xyz.ilyaxabibullin.onlinestore.view.login

import xyz.ilyaxabibullin.onlinestore.base.BasePresenter
import xyz.ilyaxabibullin.onlinestore.base.BaseView

interface LoginContract {
    interface Presenter: BasePresenter {
        fun authtorisation(login:String,password:String)
    }
    interface View: BaseView {
        fun showFailedMessage()
        fun navigateTo()
    }
}