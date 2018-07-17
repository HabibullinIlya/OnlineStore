package xyz.ilyaxabibullin.onlinestore.view.shop.create_shop

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import xyz.ilyaxabibullin.onlinestore.R
import xyz.ilyaxabibullin.onlinestore.base.BaseActivity

class CreateShopActivity: BaseActivity(){
    private lateinit var mActionBarToolBar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_shop)

        mActionBarToolBar = findViewById(R.id.toolbar_actionbar)
        setSupportActionBar(mActionBarToolBar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true);



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