package msk.android.academy.javatemplate.Dish;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import msk.android.academy.javatemplate.DTO.HitsDTO;
import msk.android.academy.javatemplate.DTO.RecipesResponse;
import msk.android.academy.javatemplate.Database.RecipeDatabase2;
import msk.android.academy.javatemplate.Database.RecipeEntity;
import msk.android.academy.javatemplate.NewDish.NewDishActivity;
import msk.android.academy.javatemplate.R;
import msk.android.academy.javatemplate.RecipeDetailsActivity;

public class DishActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    Context context;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    String LOG = "My_Log";
    private RecipeDatabase2 db;
    FloatingActionButton fb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);
        context = getBaseContext();
        db = RecipeDatabase2.getAppDatabase(this);
        initViews();
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), 1);
        recyclerView.addItemDecoration(dividerItemDecoration);

        updateRecipe();
       // loadRecipes("pork");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG, "OnStop");
        //  compositeDisposable.dispose();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    private final DishAdapter.OnItemClickListener clickListener = dish ->
    {
        //  listener.onNewsDetailsClicked(news.getUrl());
        Intent productActivityIntent = new Intent(this, RecipeDetailsActivity.class);
        productActivityIntent.putExtra("url", dish.getUrl());
        startActivity(productActivityIntent);
    };

    private final DishAdapter.OnItemClickListener1 clk = dish ->
    {
        //  listener.onNewsDetailsClicked(news.getUrl());
        db.recipeDAO().delete(dish.getUrl());
        db.productDAO().deleteById(dish.getUrl());
        updateRecipe();
    };

    public Single<List<RecipeEntity>> getRecipe() {
        db = RecipeDatabase2.getAppDatabase(this);
        Log.d(LOG,"getRecipe");
        return db.recipeDAO().getAll();
    }

    public void showDishes(List<Dish> dishes) {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setAdapter(new DishAdapter(context, dishes, clickListener,clk));
            recyclerView.setLayoutManager(new LinearLayoutManager(context));

             } /*else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {

            recyclerView.setAdapter(new DishAdapter(getContext(), news, clickListener));
            if (MainActivity.isTwoPanel) {
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
            } else {
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
            }

            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), 1);
            recyclerView.addItemDecoration(dividerItemDecoration);
        }*/
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recycler_second);
        fb=findViewById(R.id.floatingActionButton);
        fb.setOnClickListener(v->
        {
            Intent intent = new Intent(this, NewDishActivity.class);
            startActivity(intent);
            Animatoo.animateShrink(this);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateRecipe();
    }

    public void updateRecipe() {
        final Disposable newsRoomDisposable = getRecipe()
                .map(this::daoToNews)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::showDishes, this::visibleError);
        compositeDisposable.add(newsRoomDisposable);
        Log.d(LOG,"update news");
    }

    private List<Dish> daoToNews(List<RecipeEntity> recipes) {
        Log.d(LOG, "get " + recipes.size() + " news");
        List<Dish> dishes = new ArrayList<>();
        for (RecipeEntity x : recipes) {
            dishes.add(new Dish(x.getId(),x.getLable(), x.getUrl(), x.getYield(), x.getImage(), x.getTime()));
        }
        return dishes;
    }
    private void visibleError(Throwable th) {
        Log.e(LOG, th.getMessage(), th);
    }





    private RecipeEntity[] toDAO(@NonNull RecipesResponse response) {
        List<HitsDTO> listdto = response.getData();
        List<RecipeEntity> recipes = new ArrayList<RecipeEntity>();
        Log.d("MYLOG","DAO");
        Log.d("MYLOG",""+listdto.size());
        for (HitsDTO x : listdto) {
            RecipeEntity item = new RecipeEntity(x.getData().getLabel(), x.getData().getImage(), x.getData().getYield(),x.getData().getUrl(), x.getData().getTime());
            recipes.add(item);
        }
        Log.d("MYLOG","DAO finish");
        return recipes.toArray(new RecipeEntity[recipes.size()]);
    }



}
