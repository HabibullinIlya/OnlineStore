package xyz.ilyaxabibullin.onlinestore.view.shop

import xyz.ilyaxabibullin.onlinestore.entitys.retrofit.Shop

interface ShopContract {
    interface View{
        fun showShop(shop: Shop)

    }
    interface Presenter{
        fun activityWasStated(shop_id:Int)
    }
}