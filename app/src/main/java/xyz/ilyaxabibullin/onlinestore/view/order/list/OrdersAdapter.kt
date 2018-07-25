package xyz.ilyaxabibullin.onlinestore.view.order.list

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import xyz.ilyaxabibullin.onlinestore.R
import xyz.ilyaxabibullin.onlinestore.entitys.retrofit.Order
import xyz.ilyaxabibullin.onlinestore.view.product_list.OnItemClickListener

class OrdersAdapter(var orders: List<Order>) : RecyclerView.Adapter<OrdersAdapter.OrderHolder>() {
    private val TAG = javaClass.toString()


    var onItemClickListener: OnItemClickListener? = null




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.order_item, parent, false)
        v.isClickable = true
        return OrderHolder(v)
    }

    override fun getItemCount(): Int {
        return orders.size
    }

    override fun onBindViewHolder(holder: OrderHolder, i: Int) {
        holder.id.text = orders[i].id.toString()
        holder.date.text = orders[i].date.toString()
    }


    var onItemClick: ((Order) -> Unit)? = null
    inner class OrderHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)
    {

        var cv: CardView = itemView!!.findViewById(R.id.cv_order)
        var id: TextView = itemView!!.findViewById(R.id.order_id)
        var date: TextView = itemView!!.findViewById(R.id.order_date)


        init{
            itemView!!.setOnClickListener{
                onItemClick!!.invoke(orders[adapterPosition])
            }
        }
    }
}