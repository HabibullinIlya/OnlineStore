package xyz.ilyaxabibullin.onlinestore.view.userinfo

import xyz.ilyaxabibullin.onlinestore.entitys.retrofit.User

interface UserInfoContract{
    interface View{
        fun showInfo(user:User)
        fun hideToShopBtn()
        fun hideCreateShopBtn()
        fun navigateToShop()
        fun navigateToCreateShop()
    }
    interface Presenter{
        fun activityWasStarted(id:Int)
        fun toShopBtnWasClicked(id:Int)
        fun toCreateShopBtnWasClicked()
    }
}