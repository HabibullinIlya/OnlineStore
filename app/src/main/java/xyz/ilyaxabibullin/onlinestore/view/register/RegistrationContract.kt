package xyz.ilyaxabibullin.onlinestore.view.register

import xyz.ilyaxabibullin.onlinestore.base.BasePresenter
import xyz.ilyaxabibullin.onlinestore.base.BaseView

interface RegistrationContract {
    interface View : BaseView {
        fun navigate()
        fun registrationError()
    }

    interface Presenter:BasePresenter {
        fun register(email:String, fName:String, lName:String, pass: String)
    }
}