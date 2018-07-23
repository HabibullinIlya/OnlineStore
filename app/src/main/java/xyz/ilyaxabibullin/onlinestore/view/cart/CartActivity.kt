package xyz.ilyaxabibullin.onlinestore.view.cart

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_recycler.*
import xyz.ilyaxabibullin.onlinestore.R
import xyz.ilyaxabibullin.onlinestore.base.BaseActivity
import xyz.ilyaxabibullin.onlinestore.entitys.retrofit.Product
import xyz.ilyaxabibullin.onlinestore.view.product.ProductActivity
import xyz.ilyaxabibullin.onlinestore.view.product_list.OnItemClickListener
import xyz.ilyaxabibullin.onlinestore.view.product_list.ProductListAdapter

class CartActivity : BaseActivity(),CartContract.View {


    lateinit var rv:RecyclerView
    private lateinit var adapter: ProductListAdapter
    private lateinit var manager: LinearLayoutManager


    private lateinit var mActionToolbar : Toolbar

    lateinit var products: ArrayList<Product>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)

        add_prod.visibility = View.GONE

        mActionToolbar = findViewById(R.id.toolbar_actionbar)
        setSupportActionBar(mActionToolbar)




        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)



        rv = findViewById(R.id.rv)
        manager = LinearLayoutManager(this)
        rv.layoutManager = manager

        products = ArrayList()
        fakeData();

        adapter = ProductListAdapter(products,this)
        rv.adapter = adapter

        adapter.setOnItemClickListener(object: OnItemClickListener{
            override fun onItemClick(position: Int, v: View) {
               bind(position)
            }

        })
    }

    override fun showItem(item: List<Product>) {
        this.products = item as ArrayList<Product>
        adapter = ProductListAdapter(products,this)
        rv.adapter = adapter

        //TODO("")//переход к оформлению заказа
    }

    private fun bind(position: Int) {
        println(position)
        val intent = Intent(this, ProductActivity::class.java)

        intent.putExtra("product_id", products[position].id.toString())
        Log.d("CartActivity", products[position].id.toString())

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

    private fun fakeData() {
        val product = Product()
        product.id = 1
        product.price = 99999.9
        product.name = "Кот с ноутбуком"
        product.description = "Очень хороший кот, который пишет отменный код"
        // product.setLink("https://sun9-8.userapi.com/c635104/v635104289/24d73/NpaOvn9JMUE.jpg");
        product.number = 10
        products.add(product)
    }
}
