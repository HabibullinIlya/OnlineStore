package xyz.ilyaxabibullin.onlinestore.view.product

class ProductPresenter(var view:ProductContract.View): ProductContract.Presenter{
    override fun btnBuyWasClicked(id: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun btnToCartWasClicked() {

        view.addToCart()
    }

    override fun btnToShopWasClicked(id: Int) {
        view.navigateToShop()
    }
}