package xyz.ilyaxabibullin.onlinestore

import android.app.Application
import android.content.Intent
import io.realm.Realm
import io.realm.Realm.getDefaultInstance
import io.realm.RealmConfiguration
import io.realm.RealmResults
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import xyz.ilyaxabibullin.onlinestore.entitys.realm.Token


class App: Application(){

    companion object {
        var remember = false
        var id:Int? = null
        var token:String = ""
        lateinit var retrofit:Retrofit


        //temp URL
        private val baseUrl = "http://xz15e.mocklab.io"

        fun checkRemember(){
            println("чекаю в App")
            println(remember)
            if(!remember){
                var realm = getDefaultInstance()
                val result: RealmResults<Token> = realm.where(Token::class.java).findAll()
                realm.executeTransaction {
                    result.deleteAllFromRealm()
                }
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        var interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        var client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        Realm.init(this)

        Realm.setDefaultConfiguration(RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .name("onlineshop.db")
                .build())

        retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

    }




}