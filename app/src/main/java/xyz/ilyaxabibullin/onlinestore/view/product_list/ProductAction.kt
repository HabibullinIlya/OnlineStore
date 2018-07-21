package xyz.ilyaxabibullin.onlinestore.view.product_list


//пишу велосипеды, недорого...прост хз как это на самом деле делается
enum class ProductAction (
    val Action:Int
){
    DEFAULT(0),SEARCH(1),SHOP_PRODUCTS(2);
}