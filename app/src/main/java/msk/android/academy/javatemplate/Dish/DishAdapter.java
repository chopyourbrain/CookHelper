package msk.android.academy.javatemplate.Dish;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import msk.android.academy.javatemplate.R;

public class DishAdapter extends RecyclerView.Adapter<DishAdapter.ViewHolder> {
    private final List<Dish> dishes;
    private final OnItemClickListener clickListener;
    private final OnItemClickListener1 clk;
    private static final String LOG = "My_Log";
    private Context context;
    private final LayoutInflater inflater;

    public DishAdapter(Context context, List<Dish> dishes, OnItemClickListener clickListener, OnItemClickListener1 clk) {
        this.dishes = dishes;
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.clickListener = clickListener;
        this.clk=clk;
        Log.d(LOG, "Constructor recycler adapter");
    }

    @Override
    public int getItemCount() {
        return dishes.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_rec_sec, parent, false), clickListener, clk);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Dish dish = dishes.get(position);

        holder.nameView.setText(dish.getName());
        holder.timeView.setText("Время приготовления"+dish.getTime());
        if (dish.getTime().contains("0.0"))
            holder.timeView.setVisibility(View.GONE);
        holder.personsView.setText("Количество персон: "+dish.getPersons());

        Glide.with(context).load(dish.getImageUrl()).into(holder.imageView);
    }

    public interface OnItemClickListener {
        void onItemClick(Dish dish);

    }

    public interface OnItemClickListener1 {
        void onDelClick(Dish dish);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;
        private final TextView nameView;
        private final TextView timeView;
        private final TextView personsView;
        private final Button buttonDel;

        private ViewHolder(View itemView, @Nullable OnItemClickListener listener, @Nullable OnItemClickListener1 listener1) {
            super(itemView);

            itemView.setOnClickListener(view ->
            {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(dishes.get(position));
                }
            });


            imageView = itemView.findViewById(R.id.image_recept);
            nameView = itemView.findViewById(R.id.item_recept);
            timeView = itemView.findViewById(R.id.time);
            personsView = itemView.findViewById(R.id.item_yield);
            buttonDel=itemView.findViewById(R.id.button_delete);
            buttonDel.setOnClickListener(view ->
            {
                int position = getAdapterPosition();
                if (listener1 != null && position != RecyclerView.NO_POSITION) {
                    Log.d(LOG,"delclick");
                    listener1.onDelClick(dishes.get(position));
                }
            });
        }
    }


}
