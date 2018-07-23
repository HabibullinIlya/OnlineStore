package xyz.ilyaxabibullin.onlinestore.view.product

interface ProductContract {
    interface View{

        fun showProduct(name:String,description:String,amount:Int,price:Double)
        fun navigateToShop()
        fun navigateToOrder()
    }

    interface Model {

    }
    interface Presenter{
        fun activityWasStarted(id:Int)

        fun btnBuyWasClicked(id:Int)
        fun btnToCartWasClicked()
        fun btnToShopWasClicked(id:Int)

    }
}