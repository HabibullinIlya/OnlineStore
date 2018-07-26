package xyz.ilyaxabibullin.onlinestore.view.order

import xyz.ilyaxabibullin.onlinestore.entitys.retrofit.Order
import xyz.ilyaxabibullin.onlinestore.entitys.retrofit.Product

interface OrderContract {
    interface View {
        fun showOrder(order: Order)
        fun showProducts(products: List<Product>)
        fun message(msg:String)
        fun update()
    }

    interface Presenter {
        fun activityWasStarted(id:Int)
        fun payBtnWasClicked(id:Int)

    }
}