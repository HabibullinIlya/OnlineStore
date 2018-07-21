package xyz.ilyaxabibullin.onlinestore.view.product

class ProductPresenter(var view:ProductContract.View): ProductContract.Presenter{
    override fun btnBuyWasClicked(id: Int) {
        view.navigateToOrder()
    }

    override fun btnToCartWasClicked() {

        view.addToCart()
    }

    override fun btnToShopWasClicked(id: Int) {
        view.navigateToShop()
    }
}