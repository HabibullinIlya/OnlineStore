package xyz.ilyaxabibullin.onlinestore.view.shop.create_shop

import xyz.ilyaxabibullin.onlinestore.entitys.retrofit.Shop

interface CreateShopContract {
    interface View{
        fun message(msg:String)
        fun navigateToShop(id:Int)

    }
    interface Presenter{
        fun uploadInfo(shop: Shop)
    }
}