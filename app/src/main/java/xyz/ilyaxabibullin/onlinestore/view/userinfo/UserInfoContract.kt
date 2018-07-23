package xyz.ilyaxabibullin.onlinestore.view.userinfo

import xyz.ilyaxabibullin.onlinestore.entitys.retrofit.User

interface UserInfoContract{
    interface View{
        fun showInfo(user:User)
    }
    interface Presenter{
        fun activityWasStarted(id:Int)
    }
}