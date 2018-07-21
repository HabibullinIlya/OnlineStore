package xyz.ilyaxabibullin.onlinestore.view.product

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_poduct.*
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

    private var shopId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poduct)

        mActionBarToolBar = findViewById(R.id.toolbar_actionbar)
        setSupportActionBar(mActionBarToolBar)


        initWidgets()
        loadFromIntent()
    }

    private fun loadFromIntent() {
        var intent = this.intent
        nameTextView.text = intent.getStringExtra("name")
        priceTextView.text = intent.getStringExtra("price").toString()
        descriptionTextView.text = intent.getStringExtra("description")
        var imageLink = intent.getStringExtra("link")
        Glide.with(this)
                .load(imageLink)
                .into(productImage)

        numberTextView.text = intent.getStringExtra("number")
        shopId = Integer.parseInt(intent.getStringExtra("id"))

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_activity_main, menu)
        return true
    }

    fun initWidgets(){
        productImage = findViewById(R.id.image_product)
        nameTextView = findViewById(R.id.name_product)
        priceTextView = findViewById(R.id.price_product)
        numberTextView = findViewById(R.id.number_of_product)
        descriptionTextView = findViewById(R.id.description_product)

        to_shop.setOnClickListener{
            presenter.btnToShopWasClicked(shopId)
        }
        buy_now_btn.setOnClickListener {
            presenter.btnBuyWasClicked(1)
        }
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

    }

    override fun addToCart() {
        val intent = Intent(this, CartActivity::class.java)
        //intent.putExtra("id",id)
        startActivity(intent)
    }

    override fun navigateToShop() {
        val intent = Intent(this,ShopActivity::class.java)
        //intent.putExtra("id",App.userId)
        startActivity(intent)
    }

    override fun navigateToOrder() {
        val intent = Intent(this, OrderActivity::class.java)
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                this.finish();
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}