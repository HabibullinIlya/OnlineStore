package xyz.ilyaxabibullin.onlinestore.view.splash

import xyz.ilyaxabibullin.onlinestore.entitys.realm.Token

interface SplashContract{
    interface View{
        fun navigateToLogin()
        fun navigateToMyShop()
    }
    interface Presenter{
        fun activityWasStarted()
    }
    interface Model{
        fun loadTokenFromDb(): Token?
    }
}