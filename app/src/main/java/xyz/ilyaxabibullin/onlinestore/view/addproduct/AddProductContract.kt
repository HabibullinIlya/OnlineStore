package xyz.ilyaxabibullin.onlinestore.view.addproduct

import xyz.ilyaxabibullin.onlinestore.base.BaseActivity
import xyz.ilyaxabibullin.onlinestore.base.BaseView

interface AddProductContract{
    interface View: BaseView {
        fun navigateToProduct()
        fun message(message:String)
    }
    interface Presenter{
        fun buttonWasClicked(name: String, description:String, amount:Int,
                             price:Double,category: String)

        fun uploadDataFromNet(name: String, description:String, amount:Int,
                       price:Double,category: String)

    }
    interface Model{

    }
}