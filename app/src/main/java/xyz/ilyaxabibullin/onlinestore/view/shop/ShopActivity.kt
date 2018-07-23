package xyz.ilyaxabibullin.onlinestore.view.shop

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_shop.*
import xyz.ilyaxabibullin.onlinestore.R
import xyz.ilyaxabibullin.onlinestore.base.BaseActivity
import xyz.ilyaxabibullin.onlinestore.view.product_list.ProductAction
import xyz.ilyaxabibullin.onlinestore.view.product_list.ProductListActivity

class ShopActivity: BaseActivity(){

    private lateinit var mActionBarToolBar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)

        mActionBarToolBar = findViewById(R.id.toolbar_actionbar)
        setSupportActionBar(mActionBarToolBar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        to_products_btn.setOnClickListener{
            val intent = Intent(this, ProductListActivity::class.java)
            //а вот и велосипеды подъехали
            println(ProductAction.SHOP_PRODUCTS.Action.toString())
            intent.putExtra("action",ProductAction.SHOP_PRODUCTS.Action.toString())
            startActivity(intent)

        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item!!.itemId){
            android.R.id.home ->{
                this.finish()
                true
            }
            else->{
                super.onOptionsItemSelected(item)
            }
        }
    }
}