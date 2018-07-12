package xyz.ilyaxabibullin.onlinestore

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object App: Application(){
    var token:String? = null
    var id:Int? = null

    lateinit var retrofit:Retrofit
    val baseUrl = "https://ilyahabibullin.xyz"

    override fun onCreate() {
        super.onCreate()
        var interspector = HttpLoggingInterceptor()
        interspector.level = HttpLoggingInterceptor.Level.BODY
        var client = OkHttpClient.Builder().addInterceptor(interspector).build()

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