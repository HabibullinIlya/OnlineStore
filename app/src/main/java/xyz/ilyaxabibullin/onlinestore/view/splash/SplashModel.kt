package xyz.ilyaxabibullin.onlinestore.view.splash

import io.realm.Realm
import io.realm.RealmQuery
import io.realm.RealmResults
import xyz.ilyaxabibullin.onlinestore.entitys.realm.Token

class SplashModel:SplashContract.Model{

    override fun loadTokenFromDb(): Token?{
        var token:Token

        val realm: Realm = Realm.getDefaultInstance()

        val query: RealmQuery<Token>  = realm.where(Token::class.java)

        val result: RealmResults<Token> = query.findAll()

        if(result.isNotEmpty()){
            return result[0]!!;
        }
        else{
            return null;
        }
    }
}