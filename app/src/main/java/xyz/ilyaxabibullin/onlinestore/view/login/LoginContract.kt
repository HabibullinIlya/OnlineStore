package xyz.ilyaxabibullin.onlinestore.view.login

import android.content.Context
import xyz.ilyaxabibullin.onlinestore.base.BasePresenter
import xyz.ilyaxabibullin.onlinestore.base.BaseView

interface LoginContract {
    interface Presenter: BasePresenter {
        fun authorisation(login:String, password:String)
        fun regBtnWasClicked()
        fun loginBtnWasClicked()

    }
    interface View: BaseView {
        fun navigateToRegActivity()
        fun navigateToMyShop()
        fun showErrorMessage(context: Context, msg:String)
    }

    interface Model {

    }
}