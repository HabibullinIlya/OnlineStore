package xyz.ilyaxabibullin.onlinestore.view.product_list;

import android.media.Image;
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

import xyz.ilyaxabibullin.onlinestore.App;
import xyz.ilyaxabibullin.onlinestore.R;
import xyz.ilyaxabibullin.onlinestore.entitys.retrofit.Product;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.UserHolder> {
    OnItemClickListener onItemClickListener = null;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item,parent,false);
        UserHolder userHolder = new UserHolder(v);
        return userHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int i) {
        ImageView imageView = ((UserHolder)holder).itemImage;
        holder.name.setText(users.get(i).getName());
        holder.age.setText(String.valueOf(users.get(i).getPrice()));
        Glide.with(holder.itemView.getContext())
                .load(users.get(i).getLink())
                .into(imageView);

    }


    @Override
    public int getItemCount() {
        return users.size();
    }

    public class UserHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        CardView cv;
        ImageView itemImage;
        TextView name;
        TextView age;
        public UserHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            name = itemView.findViewById(R.id.name_item);
            age = itemView.findViewById(R.id.price_item);
            itemImage = itemView.findViewById(R.id.item_image);
            cv.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onItemClick(getAdapterPosition(),view);
        }
    }
    private List<Product> users;
    ProductListAdapter(List<Product> users){
        this.users = users;
    }

}
