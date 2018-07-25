package xyz.ilyaxabibullin.onlinestore.view.product

interface ProductContract {
    interface View{

        fun showProduct(name:String,description:String,amount:Int,price:Double)//TODO()//переписать через сущность
        fun navigateToShop()
        fun navigateToOrder()
        fun successMessage()
        fun errorMessage(msg:String)


    }

    interface Model {

    }
    interface Presenter{
        fun activityWasStarted(id:Int)

        fun btnBuyWasClicked(id:Int)
        fun btnToCartWasClicked(id:Int)
        fun btnToShopWasClicked(id:Int)

    }
}