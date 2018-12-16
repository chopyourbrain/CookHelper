package msk.android.academy.javatemplate.NewDish;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.SearchView;
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
import msk.android.academy.javatemplate.DTO.RecipesResponse;
import msk.android.academy.javatemplate.Database.ProductEntity;
import msk.android.academy.javatemplate.Database.RecipeDatabase;
import msk.android.academy.javatemplate.Database.RecipeDatabase2;
import msk.android.academy.javatemplate.Database.RecipeEntity;
import msk.android.academy.javatemplate.Dish.Dish;
import msk.android.academy.javatemplate.Dish.DishAdapter;
import msk.android.academy.javatemplate.NET.Network;
import msk.android.academy.javatemplate.R;

public class NewDishActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    Context context;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    String LOG = "My_Log";
    private RecipeDatabase db;
    private RecipeDatabase2 db2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dish);
        context = getBaseContext();
        db = RecipeDatabase.getAppDatabase(this);
        db2 = RecipeDatabase2.getAppDatabase(this);
        initViews();
        updateRecipe();
        loadRecipes("chicken");
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), 1);
        recyclerView.addItemDecoration(dividerItemDecoration);
        SearchView searchView = (SearchView) findViewById(R.id.search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                loadRecipes(query);
                updateRecipe();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //loadRecipes(newText);
                // updateRecipe();
                return false;
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG, "OnStop");
        //  compositeDisposable.dispose();
    }

    private final DishAdapter.OnItemClickListener clickListener = dish ->
    {
        //  listener.onNewsDetailsClicked(news.getUrl());
        db2.recipeDAO().insert(new RecipeEntity(dish.getName(), dish.getImageUrl(), dish.getPersons(), dish.getUrl(), dish.getTime()));
        db2.productDAO().deleteAll();
        db2.productDAO().insertAll(db.productDAO().getById(dish.getUrl()).toArray(new ProductEntity[db.productDAO().getById(dish.getUrl()).size()]));
       // this.finish();
    };

    public Single<List<RecipeEntity>> getRecipes() {
        db = RecipeDatabase.getAppDatabase(this);
        Log.d(LOG,"getRecipe");
        return db.recipeDAO().getAll();
    }

    public void showDishes(List<Dish> dishes) {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setAdapter(new NewDishAdapter(context, dishes, clickListener));
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
        recyclerView = findViewById(R.id.recycler_add);
    }

    public void updateRecipe() {
        final Disposable dishRoomDisposable = getRecipes()
                .map(this::daoToDishes)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::showDishes, this::visibleError);
        compositeDisposable.add(dishRoomDisposable);
        Log.d(LOG,"update dish");
    }

    private List<Dish> daoToDishes(List<RecipeEntity> recipes) {
        Log.d(LOG, "get " + recipes.size() + " recipes");
        List<Dish> dishes = new ArrayList<>();
        for (RecipeEntity x : recipes) {
            dishes.add(new Dish(x.getId(),x.getLable(), x.getUrl(), x.getYield(), x.getImage(),x.getTime()));
        }
        return dishes;
    }
    private void visibleError(Throwable th) {
        Log.e(LOG, th.getMessage(), th);
    }


    private void loadRecipes(@NonNull String search) {

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
        Log.d(LOG, "save Recipes");

        db.productDAO().deleteAll();
        db.productDAO().insertAll(recipes);
        Log.d(LOG, "save " + recipes.length + " news to DB");
    }


}
