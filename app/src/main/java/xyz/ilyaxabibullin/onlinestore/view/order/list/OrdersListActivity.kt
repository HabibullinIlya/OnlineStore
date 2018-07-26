package xyz.ilyaxabibullin.onlinestore.view.order.list

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_recycler.*
import xyz.ilyaxabibullin.onlinestore.R
import xyz.ilyaxabibullin.onlinestore.base.BaseActivity
import xyz.ilyaxabibullin.onlinestore.entitys.retrofit.Order
import xyz.ilyaxabibullin.onlinestore.view.order.OrderActivity

class OrdersListActivity : BaseActivity(), OrdersListContract.View {

    private val TAG = javaClass.toString()

    var presenter: OrdersListContract.Presenter = OrdersListPresenter(this)

    private lateinit var adapter: OrdersAdapter
    private lateinit var rv: RecyclerView
    private lateinit var manager: LinearLayoutManager
    private lateinit var mActionToolbar: Toolbar

    var orders = ArrayList<Order>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)

        mActionToolbar = findViewById(R.id.toolbar_actionbar)
        setSupportActionBar(mActionToolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        add_prod.visibility = View.GONE

        presenter.activityWasStarted()

        manager = LinearLayoutManager(this)
        rv = findViewById(R.id.rv)
        rv.layoutManager = manager
        adapter = OrdersAdapter(orders)
        rv.adapter = adapter




    }

    override fun showItem(items: List<Order>) {
        this.orders = items as ArrayList<Order>
        adapter = OrdersAdapter(orders)
        rv.adapter = adapter

        adapter.onItemClick = {
            Log.d(TAG,it.id.toString())
            val intent = Intent(this@OrdersListActivity, OrderActivity::class.java)
            intent.putExtra("order_id",it.id.toString())
            startActivity(intent)
        }


    }
    override fun onResume() {
        super.onResume()
        Log.d(TAG,"onResume")
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
