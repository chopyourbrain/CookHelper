package msk.android.academy.javatemplate.Product;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import msk.android.academy.javatemplate.Dish.Dish;

import msk.android.academy.javatemplate.R;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private final List<Product> products;
    private static final String LOG = "My_Log";
    private Context context;
    private final ProductAdapter.OnItemClickListener clickListener;
    private final LayoutInflater inflater;

    public ProductAdapter(Context context, List<Product> products, ProductAdapter.OnItemClickListener clickListener) {
        this.products = products;
        this.context = context;
        this.clickListener=clickListener;
        inflater = LayoutInflater.from(context);

        Log.d(LOG, "Constructor recycler adapter products");
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ProductAdapter.ViewHolder(inflater.inflate(R.layout.item_products, parent, false), clickListener);
    }

    @Override
    public void onBindViewHolder(ProductAdapter.ViewHolder holder, int position) {
        Product product = products.get(position);

        holder.nameView.setText(product.getName());
        holder.countView.setText(product.getWeight()+"");
      //  holder.balanceView.setText(product.getBalance()+"");
        holder.cb.setChecked(product.getCheck());
    }
    public interface OnItemClickListener {
        void onItemClick(Product product);

    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameView;
        private final TextView countView;
       // private final TextView balanceView;
        private final Button butMinus;
        private final Button butPlus;
        private final CheckBox cb;

        private ViewHolder(View itemView, @Nullable ProductAdapter.OnItemClickListener listener) {
            super(itemView);

            nameView = itemView.findViewById(R.id.item_products);
            countView = itemView.findViewById(R.id.need);
           // balanceView = itemView.findViewById(R.id.count_need);
            butMinus=itemView.findViewById(R.id.minus_button);
            butPlus=itemView.findViewById(R.id.plus_button);
            cb=itemView.findViewById(R.id.check_box);

            cb.setOnClickListener(view ->
            {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(products.get(position));
                }
            });
        }
    }


}
