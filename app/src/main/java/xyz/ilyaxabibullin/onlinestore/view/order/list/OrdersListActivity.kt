package xyz.ilyaxabibullin.onlinestore.view.order.list

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_recycler.*
import xyz.ilyaxabibullin.onlinestore.R
import xyz.ilyaxabibullin.onlinestore.base.BaseActivity
import xyz.ilyaxabibullin.onlinestore.entitys.retrofit.Order
import xyz.ilyaxabibullin.onlinestore.view.order.OrderActivity

class OrdersListActivity : BaseActivity(), OrdersListContract.View {

    private val TAG = javaClass.toString()

    var presenter: OrdersListContract.Presenter = OrdersListPresenter(this)

    lateinit var adapter: OrdersAdapter
    lateinit var rv: RecyclerView
    lateinit var manager: LinearLayoutManager

    var orders = ArrayList<Order>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)

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
            var intent = Intent(this@OrdersListActivity, OrderActivity::class.java)
            intent.putExtra("order_id",it.id.toString())
            startActivity(intent)
        }


    }
    override fun onResume() {
        super.onResume()
        Log.d(TAG,"onPostResume")
    }


}
