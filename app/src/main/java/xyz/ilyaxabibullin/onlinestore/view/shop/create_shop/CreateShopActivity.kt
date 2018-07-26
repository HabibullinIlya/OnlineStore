package xyz.ilyaxabibullin.onlinestore.view.shop.create_shop

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.Toolbar

import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_create_shop.*

import xyz.ilyaxabibullin.onlinestore.R
import xyz.ilyaxabibullin.onlinestore.base.BaseActivity
import xyz.ilyaxabibullin.onlinestore.entitys.retrofit.Shop
import xyz.ilyaxabibullin.onlinestore.view.shop.ShopActivity


class CreateShopActivity: BaseActivity(),CreateShopContract.View{



    private val TAG = javaClass.toString()
    private lateinit var mActionBarToolBar: Toolbar

    private val presenter: CreateShopContract.Presenter = CreateShopPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_shop)

        mActionBarToolBar = findViewById(R.id.toolbar_actionbar)
        setSupportActionBar(mActionBarToolBar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        create_shop_btn.setOnClickListener{
            val shop = Shop()
            shop.name = shop_name_edit.text.toString()
            shop.description = shop_description_edit.text.toString()
            presenter.uploadInfo(shop)
        }
    }


    override fun message(msg: String) = Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()

    override fun navigateToShop(id:Int) {
        val intent = Intent(this, ShopActivity::class.java)
        intent.putExtra("shop_id",id.toString())
        startActivity(intent)
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