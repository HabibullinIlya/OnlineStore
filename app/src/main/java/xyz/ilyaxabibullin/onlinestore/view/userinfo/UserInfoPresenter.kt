package xyz.ilyaxabibullin.onlinestore.view.userinfo

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import xyz.ilyaxabibullin.onlinestore.App
import xyz.ilyaxabibullin.onlinestore.entitys.retrofit.User
import xyz.ilyaxabibullin.onlinestore.entitys.retrofit.UserResponse
import xyz.ilyaxabibullin.onlinestore.network.UserApi

class UserInfoPresenter(var view: UserInfoContract.View) : UserInfoContract.Presenter {
    override fun toShopBtnWasClicked(id: Int) {
        view.navigateToShop()
    }

    override fun toCreateShopBtnWasClicked() {
        view.navigateToCreateShop()
    }

    override fun activityWasStarted(id: Int) {
        App.retrofit.create(UserApi::class.java).getUser(id).enqueue(object : Callback<UserResponse> {
            override fun onFailure(call: Call<UserResponse>?, t: Throwable?) {
                t!!.printStackTrace()
            }

            override fun onResponse(call: Call<UserResponse>?, response: Response<UserResponse>?) {
                if (response!!.isSuccessful) {
                    val user: User = response.body()!!
                            .user!!
                    view.showInfo(user)
                    if (user.isShopOwner){
                        view.hideCreateShopBtn()
                    }
                }
            }

        })
    }

}