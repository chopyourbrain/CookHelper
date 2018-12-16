package msk.android.academy.javatemplate.Product;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;
import msk.android.academy.javatemplate.R;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private final List<Product> products;
    private static final String LOG = "My_Log";
    private Context context;
    private final LayoutInflater inflater;

    public ProductAdapter(Context context, List<Product> products) {
        this.products = products;
        this.context = context;
        inflater = LayoutInflater.from(context);

        Log.d(LOG, "Constructor recycler adapter products");
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ProductAdapter.ViewHolder(inflater.inflate(R.layout.item_rec_sec, parent, false));
    }

    @Override
    public void onBindViewHolder(ProductAdapter.ViewHolder holder, int position) {
        Product product = products.get(position);

        holder.nameView.setText(product.getName());
        holder.countView.setText("Количество "+product.getWeight());
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameView;
        private final TextView countView;

        private ViewHolder(View itemView) {
            super(itemView);

            nameView = itemView.findViewById(R.id.item_recept);
           countView = itemView.findViewById(R.id.item_yield);
        }
    }


}
