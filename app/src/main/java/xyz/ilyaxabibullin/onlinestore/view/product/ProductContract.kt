package xyz.ilyaxabibullin.onlinestore.view.product

interface ProductContract {
    interface View{
        fun addToCart()
        fun navigateToShop()
        fun navigateToOrder()
    }

    interface Model {

    }
    interface Presenter{
        fun btnBuyWasClicked(id:Int)
        fun btnToCartWasClicked()
        fun btnToShopWasClicked(id:Int)
    }
}