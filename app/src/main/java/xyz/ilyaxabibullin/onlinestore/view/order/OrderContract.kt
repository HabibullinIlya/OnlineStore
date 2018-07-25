package xyz.ilyaxabibullin.onlinestore.view.order

import xyz.ilyaxabibullin.onlinestore.entitys.retrofit.Order
import xyz.ilyaxabibullin.onlinestore.entitys.retrofit.Product

interface OrderContract {
    interface View {
        fun showOrder(order: Order)
        fun showProducts(products: List<Product>)
    }

    interface Presenter {
        fun activityWasStarted(id:Int)

    }
}