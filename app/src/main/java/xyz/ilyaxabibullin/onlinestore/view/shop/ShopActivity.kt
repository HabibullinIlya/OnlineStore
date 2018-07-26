package xyz.ilyaxabibullin.onlinestore.view.shop

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_profile_info.*
import kotlinx.android.synthetic.main.activity_shop.*
import xyz.ilyaxabibullin.onlinestore.R
import xyz.ilyaxabibullin.onlinestore.base.BaseActivity
import xyz.ilyaxabibullin.onlinestore.entitys.retrofit.Shop
import xyz.ilyaxabibullin.onlinestore.view.product_list.ProductAction
import xyz.ilyaxabibullin.onlinestore.view.product_list.ProductListActivity

class ShopActivity: BaseActivity(),ShopContract.View{


    private lateinit var mActionBarToolBar: Toolbar

    private var presenter: ShopContract.Presenter = ShopPresenter(this)

    private var shop = Shop()

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
            intent.putExtra("shop_id",shop.owner.id.toString())
            startActivity(intent)

        }
        shop.id = Integer.parseInt(intent.getStringExtra("shop_id"))
        presenter.activityWasStated(shop.id)
    }

    override fun showShop(shop: Shop) {
        this.shop = shop
        shop_name.text = shop.name
        shop_description.text = shop.description

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