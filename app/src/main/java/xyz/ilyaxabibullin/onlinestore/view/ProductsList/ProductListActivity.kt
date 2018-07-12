package xyz.ilyaxabibullin.onlinestore.view.ProductsList

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import xyz.ilyaxabibullin.onlinestore.R
import xyz.ilyaxabibullin.onlinestore.base.BaseActivity
import xyz.ilyaxabibullin.onlinestore.entitys.retrofit.Product

class ProductListActivity: BaseActivity(), ProductListContract{

    lateinit var rv: RecyclerView
    lateinit var adapter: ProductAdapter
    var items = ArrayList<Product>()
    private lateinit var linearLayoutManager: LinearLayoutManager

    private lateinit var mActionBarToolBar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recycler_view)

        mActionBarToolBar = findViewById(R.id.toolbar_actionbar)
        setSupportActionBar(mActionBarToolBar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true);

        fakeData()
        println(items[0].name)
        adapter = ProductAdapter(items)
        linearLayoutManager = LinearLayoutManager(this)
        rv = findViewById(R.id.rv)
        rv.setHasFixedSize(true)
        rv.layoutManager = linearLayoutManager
        rv.adapter = adapter
        adapter.notifyDataSetChanged()

    }
    private fun fakeData(){
        var product = Product()
        println("tut")
        product.name = "kokshnik"
        product.price = 399.99
        items.add(product)
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