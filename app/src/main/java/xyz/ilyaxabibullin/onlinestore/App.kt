package xyz.ilyaxabibullin.onlinestore

import android.app.Application
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object App: Application(){
    lateinit var retrofit:Retrofit
    val baseUrl = "https://ilyahabibullin.xyz"

    override fun onCreate() {
        super.onCreate()
        var interspector = HttpLoggingInterceptor()
        interspector.level = HttpLoggingInterceptor.Level.BODY
        var client = OkHttpClient.Builder().addInterceptor(interspector).build()

        retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
    }
}