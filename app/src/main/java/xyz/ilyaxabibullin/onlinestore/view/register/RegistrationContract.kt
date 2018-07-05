package xyz.ilyaxabibullin.onlinestore.view.register

import xyz.ilyaxabibullin.onlinestore.base.BasePresenter
import xyz.ilyaxabibullin.onlinestore.base.BaseView

interface RegistrationContract {
    interface View : BaseView {
        fun showFailMessage()
        fun navigate()
    }

    interface Presenter:BasePresenter {
        fun register(fields: Array<String>)
    }
}