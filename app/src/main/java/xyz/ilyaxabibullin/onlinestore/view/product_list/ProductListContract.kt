package xyz.ilyaxabibullin.onlinestore.view.product_list

import xyz.ilyaxabibullin.onlinestore.entitys.retrofit.Product

interface ProductListContract {
    interface Presenter {
        fun searchProducts(searchQuery: String)
        fun defaultLoadProducts()
        fun queryWasChanged(searchQuery: String)
        fun scrolledToEnd()

        fun activityStarted(action:Int)

    }

    interface View {
        fun loadNextPage(products: ArrayList<Product>)
        fun showItems(items: ArrayList<Product>);
    }
}