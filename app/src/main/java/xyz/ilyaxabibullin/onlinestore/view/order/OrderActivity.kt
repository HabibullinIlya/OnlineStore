package xyz.ilyaxabibullin.onlinestore.view.order


import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_order.*



import xyz.ilyaxabibullin.onlinestore.R
import xyz.ilyaxabibullin.onlinestore.R.layout.activity_order
import xyz.ilyaxabibullin.onlinestore.base.BaseActivity
import xyz.ilyaxabibullin.onlinestore.entitys.retrofit.Order
import xyz.ilyaxabibullin.onlinestore.entitys.retrofit.Product
import xyz.ilyaxabibullin.onlinestore.view.order.list.OrdersListActivity
import xyz.ilyaxabibullin.onlinestore.view.product.ProductActivity
import xyz.ilyaxabibullin.onlinestore.view.product_list.OnItemClickListener
import xyz.ilyaxabibullin.onlinestore.view.product_list.ProductListAdapter

class OrderActivity : BaseActivity(),OrderContract.View {


    private val TAG = javaClass.toString()

    private lateinit var mActionToolbar: Toolbar
    private lateinit var rv: RecyclerView
    private lateinit var adapter: ProductListAdapter
    private lateinit var manager: LinearLayoutManager

    lateinit var order: Order
    var products = ArrayList<Product>()

    private var presenter: OrderContract.Presenter = OrderPresenter(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        mActionToolbar = findViewById(R.id.toolbar_actionbar)
        setSupportActionBar(mActionToolbar)


        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        order = Order()
        order.id = Integer.parseInt(intent.getStringExtra("order_id"))


        rv = findViewById(R.id.rv_order)
        manager = LinearLayoutManager(this)
        rv.layoutManager = manager
        adapter = ProductListAdapter(products,this)
        rv.adapter = adapter

        presenter.activityWasStarted(order.id)

        val orderBtn: Button = findViewById(R.id.order)
        orderBtn.setOnClickListener{
            presenter.payBtnWasClicked(order.id)
        }



    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,"onPostResume")
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

    override fun showOrder(order: Order) {
        id_product_ord_n.text = order.id.toString()
        order_date_.text = order.date.toString()
        is_paid.text = order.isPaid.toString()
        status.text = order.isDone.toString()
    }

    override fun message(msg: String) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
    }

    override fun update() {
        presenter.activityWasStarted(order.id)
    }

    override fun showProducts(products: List<Product>) {
        this.products = products as ArrayList<Product>
        adapter = ProductListAdapter(products,this)
        rv.adapter = adapter
        adapter.setOnItemClickListener(object: OnItemClickListener{
            override fun onItemClick(position: Int, v: View) {
                Log.d(TAG,"kek")
                val intent = Intent(this@OrderActivity, ProductActivity::class.java)
                intent.putExtra("product_id",products[position].id.toString())
                startActivity(intent)
            }

        })


    }

}
