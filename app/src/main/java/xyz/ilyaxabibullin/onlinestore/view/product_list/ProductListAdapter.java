package xyz.ilyaxabibullin.onlinestore.view.product_list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import xyz.ilyaxabibullin.onlinestore.R;
import xyz.ilyaxabibullin.onlinestore.entitys.retrofit.Product;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.UserHolder> {


    private static final int ITEM = 0;
    private static final int LOADING = 1;

    private Context context;

    private boolean isLoadingAdded = false;

    OnItemClickListener onItemClickListener = null;

    private List<Product> products;

    ProductListAdapter(List<Product> products, Context context) {
        this.products = products;
        this.context = context;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        UserHolder userHolder = new UserHolder(v);
        return userHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int i) {
        ImageView imageView = ((UserHolder) holder).itemImage;
        holder.name.setText(products.get(i).getName());
        holder.price.setText(String.valueOf(products.get(i).getPrice()));
        Glide.with(holder.itemView.getContext())
                .load(products.get(i).getLink())
                .into(imageView);

    }


    @Override
    public int getItemCount() {
        return products.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == products.size() - 1 && isLoadingAdded) ? LOADING : ITEM;

    }

    public class UserHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView cv;
        ImageView itemImage;
        TextView name;
        TextView price;

        public UserHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            name = itemView.findViewById(R.id.name_item);
            price = itemView.findViewById(R.id.price_item);
            itemImage = itemView.findViewById(R.id.item_image);
            cv.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onItemClick(getAdapterPosition(), view);
        }
    }


    //---------------------------------------------------------------------------------------------
    //helpers

    public void add(Product product) {
        products.add(product);
        notifyItemInserted(products.size() - 1);
    }

    public void addAll(List<Product> mcList) {
        for (Product mc : mcList) {
            add(mc);
        }
    }

    public void remove(Product city) {
        int position = products.indexOf(city);
        if (position > -1) {
            products.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        isLoadingAdded = false;
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }


    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new Product());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = products.size() - 1;
        Product item = getItem(position);

        if (item != null) {
            products.remove(position);
            notifyItemRemoved(position);
        }
    }

    public Product getItem(int position) {
        return products.get(position);
    }

}
