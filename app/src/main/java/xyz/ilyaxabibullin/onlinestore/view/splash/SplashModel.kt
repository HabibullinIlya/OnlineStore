package xyz.ilyaxabibullin.onlinestore.view.splash

import xyz.ilyaxabibullin.onlinestore.entitys.realm.Token

class SplashModel:SplashContract.Model{

    override fun loadTokenFromDb(): Token?{
        var token = null
        return token;
    }
}