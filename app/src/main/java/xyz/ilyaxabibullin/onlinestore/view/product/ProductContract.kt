package xyz.ilyaxabibullin.onlinestore.view.product

import xyz.ilyaxabibullin.onlinestore.entitys.retrofit.Product

interface ProductContract {
    interface View {

        fun showProduct(product: Product)
        fun navigateToShop()
        fun successMessage()
        fun errorMessage(msg: String)


    }

    interface Model {

    }

    interface Presenter {
        fun activityWasStarted(id: Int)
        fun btnToCartWasClicked(id: Int)
        fun btnToShopWasClicked(id: Int)

    }
}