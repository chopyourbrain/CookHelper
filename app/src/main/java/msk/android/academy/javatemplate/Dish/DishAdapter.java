package msk.android.academy.javatemplate.Dish;

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

public class DishAdapter extends RecyclerView.Adapter<DishAdapter.ViewHolder> {
    private final List<Dish> dishes;
    private final OnItemClickListener clickListener;
    private static final String LOG = "My_Log";
    private Context context;
    private final LayoutInflater inflater;

    public DishAdapter(Context context, List<Dish> dishes, OnItemClickListener clickListener) {
        this.dishes = dishes;
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.clickListener = clickListener;
        Log.d(LOG, "Constructor recycler adapter");
    }

    @Override
    public int getItemCount() {
        return dishes.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_news, parent, false), clickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Dish dish = dishes.get(position);

        holder.nameView.setText(dish.getName());
        holder.timeView.setText(dish.getTime());
        holder.personsView.setText(dish.getPersons());

        Glide.with(context).load(dish.getImageUrl()).into(holder.imageView);
    }

    public interface OnItemClickListener {
        void onItemClick(Dish dish);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;
        private final TextView nameView;
        private final TextView timeView;
        private final TextView personsView;

        private ViewHolder(View itemView, @Nullable OnItemClickListener listener) {
            super(itemView);

            itemView.setOnClickListener(view ->
            {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(dishes.get(position));
                }
            });

            imageView = itemView.findViewById(R.id.image_news);
            nameView = itemView.findViewById(R.id.name_news);
            timeView = itemView.findViewById(R.id.title_news);
            personsView = itemView.findViewById(R.id.preview_news);
        }
    }


}
