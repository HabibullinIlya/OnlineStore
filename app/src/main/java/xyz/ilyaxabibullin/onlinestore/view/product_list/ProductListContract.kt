package xyz.ilyaxabibullin.onlinestore.view.product_list

import xyz.ilyaxabibullin.onlinestore.entitys.retrofit.Product

interface ProductListContract {
    interface Presenter {
        fun searchProducts(searchQuery: String): List<Product>;
        fun defaultLoadProducts():List<Product>;
        fun queryWasChanged(searchQuery: String);
    }

    interface View {

        fun showItems(items: List<Product>);
    }
}