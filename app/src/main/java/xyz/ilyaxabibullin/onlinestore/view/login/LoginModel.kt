package xyz.ilyaxabibullin.onlinestore.view.login

import io.realm.Realm
import xyz.ilyaxabibullin.onlinestore.entitys.realm.Token

class LoginModel:LoginContract.Model{

    override fun saveTokenToDB(_token:String){
        val realm:Realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        val token: Token = realm.createObject(Token::class.java)
        token.token = _token
        realm.commitTransaction()
    }

}