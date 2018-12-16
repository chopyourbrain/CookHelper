package msk.android.academy.javatemplate.NewDish;

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

import msk.android.academy.javatemplate.Dish.Dish;
import msk.android.academy.javatemplate.Dish.DishAdapter;
import msk.android.academy.javatemplate.R;

public class NewDishAdapter extends RecyclerView.Adapter<NewDishAdapter.ViewHolder> {
    private final List<Dish> dishes;
    private final DishAdapter.OnItemClickListener clickListener;
    private static final String LOG = "My_Log";
    private Context context;
    private final LayoutInflater inflater;

    public NewDishAdapter(Context context, List<Dish> dishes, DishAdapter.OnItemClickListener clickListener) {
        this.dishes = dishes;
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.clickListener = clickListener;
        Log.d(LOG, "Constructor recycler adapter new dish");
    }

    @Override
    public int getItemCount() {
        return dishes.size();
    }

    @Override
    public NewDishAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NewDishAdapter.ViewHolder(inflater.inflate(R.layout.jghgg, parent, false), clickListener);
    }

    @Override
    public void onBindViewHolder(NewDishAdapter.ViewHolder holder, int position) {
        Dish dish = dishes.get(position);

        holder.nameView.setText(dish.getName());
        holder.timeView.setText("Время приготовления"+dish.getTime());
        holder.personsView.setText("Количество персон: "+dish.getPersons());

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
        private final Button buttonAdd;

        private ViewHolder(View itemView, @Nullable DishAdapter.OnItemClickListener listener) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image_recept);
            nameView = itemView.findViewById(R.id.item_recept);
            timeView = itemView.findViewById(R.id.time);
            personsView = itemView.findViewById(R.id.mkjk);
            buttonAdd=itemView.findViewById(R.id.fsf);

            buttonAdd.setOnClickListener(view ->
            {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(dishes.get(position));
                }
            });
        }
    }
}
