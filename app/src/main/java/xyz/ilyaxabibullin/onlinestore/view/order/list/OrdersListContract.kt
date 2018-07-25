package xyz.ilyaxabibullin.onlinestore.view.order.list

import xyz.ilyaxabibullin.onlinestore.entitys.retrofit.Order

interface OrdersListContract {
    interface View{
        fun showItem(items:List<Order>)
    }
    interface Presenter{
        fun activityWasStarted()
    }
}