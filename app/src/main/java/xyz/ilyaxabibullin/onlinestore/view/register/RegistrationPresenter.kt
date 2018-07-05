package xyz.ilyaxabibullin.onlinestore.view.register

class RegistrationPresenter:RegistrationContract.Presenter {

    val view: RegistrationContract.View

    constructor(_View:RegistrationContract.View){
        view = _View
    }

    override fun register(fields: Array<String>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}