package xyz.ilyaxabibullin.onlinestore.view.product

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_poduct.*

import xyz.ilyaxabibullin.onlinestore.R
import xyz.ilyaxabibullin.onlinestore.base.BaseActivity

class ProductActivity: BaseActivity() {
    private lateinit var mActionBarToolBar: Toolbar

    private lateinit var productImage: ImageView
    private lateinit var nameTextView: TextView
    private lateinit var priceTextView: TextView
    private lateinit var numberTextView: TextView
    private lateinit var descriptionTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poduct)

        mActionBarToolBar = findViewById(R.id.toolbar_actionbar)
        setSupportActionBar(mActionBarToolBar)
        initWidgets()
        loadFromIntent()
    }

    private fun loadFromIntent() {
        var intent = getIntent()
        nameTextView.text = intent.getStringExtra("name")
        priceTextView.text = intent.getStringExtra("price").toString()
        descriptionTextView.text = intent.getStringExtra("description")
        var imageLink = intent.getStringExtra("link")
        Glide.with(this)
                .load(imageLink)
                .into(productImage)

        numberTextView.text = intent.getStringExtra("number")

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

    }

}