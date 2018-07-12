package xyz.ilyaxabibullin.onlinestore.view.ProductsList

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import xyz.ilyaxabibullin.onlinestore.R
import xyz.ilyaxabibullin.onlinestore.entitys.retrofit.Product

class ProductAdapter(private var items: ArrayList<Product>): RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var product = items[position]
        holder?.name?.text = product.name
        holder?.price?.text = product.price.toString()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent?.context)
                .inflate(R.layout.product_item, parent, false)

        return ViewHolder(itemView)
    }

    class ViewHolder(row: View) : RecyclerView.ViewHolder(row) {
        var cardView: CardView? = null
        var name: TextView? = null
        var price: TextView? = null

        init {

            this.cardView = row?.findViewById(R.id.cv)
            this.name = row?.findViewById(R.id.name_product_item)
            this.price = row?.findViewById<TextView>(R.id.price_of_item)
        }
    }
}