package xyz.ilyaxabibullin.onlinestore.view.product

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_poduct.*
import xyz.ilyaxabibullin.onlinestore.App
import xyz.ilyaxabibullin.onlinestore.view.order.OrderActivity

import xyz.ilyaxabibullin.onlinestore.R
import xyz.ilyaxabibullin.onlinestore.base.BaseActivity
import xyz.ilyaxabibullin.onlinestore.view.cart.CartActivity
import xyz.ilyaxabibullin.onlinestore.view.shop.ShopActivity

class ProductActivity: BaseActivity(),ProductContract.View {



    private var presenter:ProductContract.Presenter = ProductPresenter(this)


    private lateinit var mActionBarToolBar: Toolbar

    private lateinit var productImage: ImageView
    private lateinit var nameTextView: TextView
    private lateinit var priceTextView: TextView
    private lateinit var numberTextView: TextView
    private lateinit var descriptionTextView: TextView

    private var productId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poduct)

        mActionBarToolBar = findViewById(R.id.toolbar_actionbar)
        setSupportActionBar(mActionBarToolBar)


        initWidgets()
        loadFromIntent()
        println("productId = $productId")
        presenter.activityWasStarted(productId)

    }

    private fun loadFromIntent() {
        val intent = this.intent
        productId = Integer.parseInt(intent.getStringExtra("product_id"))

    }


    private fun initWidgets(){
        productImage = findViewById(R.id.image_product)
        nameTextView = findViewById(R.id.name_product)
        priceTextView = findViewById(R.id.price_product)
        numberTextView = findViewById(R.id.number_of_product)
        descriptionTextView = findViewById(R.id.description_product)

        to_shop.setOnClickListener{
            presenter.btnToShopWasClicked(productId)
        }
        buy_now_btn.setOnClickListener {
            presenter.btnBuyWasClicked(productId)
        }
        to_basket_btn.setOnClickListener{
            presenter.btnToCartWasClicked(productId)
        }
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

    }

    override fun showProduct(name: String, description: String, amount: Int, price: Double) {
        name_product.text = name
        price_product.text = price.toString()
        number_of_product.text = amount.toString()
        description_product.text = amount.toString()
    }

    override fun successMessage() {
        Toast.makeText(this,"product add to cart",Toast.LENGTH_SHORT)
                .show()
    }

    override fun errorMessage(msg: String) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
    }

    override fun navigateToShop() {
        val intent = Intent(this,ShopActivity::class.java)
        intent.putExtra("id", App.id)
        startActivity(intent)
    }

    override fun navigateToOrder() {
        val intent = Intent(this, OrderActivity::class.java)
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                this.finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}