package msk.android.academy.javatemplate.Dish;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import msk.android.academy.javatemplate.DTO.HitsDTO;
import msk.android.academy.javatemplate.DTO.IngredientsDTO;
import msk.android.academy.javatemplate.DTO.RecipesDTO;
import msk.android.academy.javatemplate.DTO.RecipesResponse;
import msk.android.academy.javatemplate.Database.ProductEntity;
import msk.android.academy.javatemplate.Database.RecipeDAO;
import msk.android.academy.javatemplate.Database.RecipeDatabase;
import msk.android.academy.javatemplate.Database.RecipeEntity;
import msk.android.academy.javatemplate.NET.Network;
import msk.android.academy.javatemplate.Product.ProductActivity;
import msk.android.academy.javatemplate.R;

public class DishActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    Context context;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    String LOG = "My_Log";
    private RecipeDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);
        context = getBaseContext();
        db = RecipeDatabase.getAppDatabase(this);
        initViews();
        updateRecipe();
        loadRecipes("pork");
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
        Intent productActivityIntent = new Intent(this, ProductActivity.class);
        productActivityIntent.putExtra("url", dish.getUrl());
        startActivity(productActivityIntent);
    };

    public Single<List<RecipeEntity>> getRecipe() {
        db = RecipeDatabase.getAppDatabase(this);
        Log.d(LOG,"getRecipe");
        return db.recipeDAO().getAll();
    }

    public void showDishes(List<Dish> dishes) {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setAdapter(new DishAdapter(context, dishes, clickListener));
            recyclerView.setLayoutManager(new LinearLayoutManager(context));

            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), 1);
            recyclerView.addItemDecoration(dividerItemDecoration);
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
            dishes.add(new Dish(x.getId(),x.getLable(), x.getUrl(), x.getYield(), x.getImage()));
        }
        return dishes;
    }
    private void visibleError(Throwable th) {
        Log.e(LOG, th.getMessage(), th);
    }


    private void loadRecipes(@NonNull String search) {
Log.d(LOG,"load");
        final Disposable searchDisposable = Network.getInstance()
                .recipes()
                .search(search)
                .map(this::toDAO)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::saveRecipes, this::handleError);
        compositeDisposable.add(searchDisposable);
    }

    private void handleError(Throwable throwable) {
        if (throwable instanceof IOException) {
            Toast.makeText(this, "ERROR: Check your internet connection", Toast.LENGTH_LONG);
            return;
        }
    }


    private RecipeEntity[] toDAO(@NonNull RecipesResponse response) {
        List<HitsDTO> listdto = response.getData();
        List<RecipeEntity> recipes = new ArrayList<RecipeEntity>();
        List<ProductEntity> products = new ArrayList<ProductEntity>();
        List<IngredientsDTO> ingredients = new ArrayList<IngredientsDTO>();
        Log.d("MYLOG","DAO");
        Log.d("MYLOG","size: "+listdto.size());
        for (HitsDTO x : listdto) {
            RecipeEntity item = new RecipeEntity(x.getData().getLabel(), x.getData().getImage(), x.getData().getYield(),x.getData().getUrl(),x.getData().getTime());
            recipes.add(item);
            for (IngredientsDTO y : x.getData().getIngredients()) {
                ProductEntity productEntity = new ProductEntity(y.getText(), x.getData().getUrl(), y.getWeight(), y.getWeight(), false);
                products.add(productEntity);
            }
        }
        saveIngridients(products.toArray(new ProductEntity[products.size()]));
        Log.d("MYLOG","DAO finish");
        return recipes.toArray(new RecipeEntity[recipes.size()]);
    }

    public void saveRecipes(RecipeEntity[] recipes) {
        Log.d(LOG, "save Recipes");

        db.recipeDAO().deleteAll();
        db.recipeDAO().insertAll(recipes);
        Log.d(LOG, "save " + recipes.length + " news to DB");
    }

    public void saveIngridients(ProductEntity[] recipes) {
        Log.d(LOG, "save Recipes2");

        db.productDAO().deleteAll();
        db.productDAO().insertAll(recipes);
        Log.d(LOG, "save " + recipes.length + " news to DB");
    }

}
