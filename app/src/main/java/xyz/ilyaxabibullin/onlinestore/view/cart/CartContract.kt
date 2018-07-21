package xyz.ilyaxabibullin.onlinestore.view.cart

import xyz.ilyaxabibullin.onlinestore.entitys.retrofit.Product

interface CartContract{
    interface View {
        fun showItem(item: List<Product>)
    }
    interface Presenter{
        fun activityStarted()
    }
}