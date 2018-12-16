package msk.android.academy.javatemplate.Product;


import android.content.Context;
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
import msk.android.academy.javatemplate.DTO.RecipesDTO;
import msk.android.academy.javatemplate.DTO.RecipesResponse;
import msk.android.academy.javatemplate.Database.ProductEntity;
import msk.android.academy.javatemplate.Database.RecipeDatabase;
import msk.android.academy.javatemplate.Database.RecipeEntity;
import msk.android.academy.javatemplate.Dish.Dish;
import msk.android.academy.javatemplate.Dish.DishAdapter;
import msk.android.academy.javatemplate.NET.Network;
import msk.android.academy.javatemplate.R;

public class ProductActivity extends AppCompatActivity {
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
        updateProducts();
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG, "OnStop");
        //  compositeDisposable.dispose();
    }
    public void showProducts(List<Product> products) {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setAdapter(new ProductAdapter(context, products));
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
        recyclerView = findViewById(R.id.item_recept);
    }

    public void updateProducts() {
        final Disposable newsRoomDisposable = getProducts()
                .map(this::daoToProduct)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::showProducts, this::visibleError);
        compositeDisposable.add(newsRoomDisposable);
        Log.d(LOG,"update news");
    }

    public Single<List<ProductEntity>> getProducts() {
        db = RecipeDatabase.getAppDatabase(this);
        Log.d(LOG,"getRecipe");
        return db.productDAO().getAll();
    }

    private List<Product> daoToProduct(List<ProductEntity> products_) {
        Log.d(LOG, "get " + products_.size() + " product");
        List<Product> products = new ArrayList<>();
        for (ProductEntity x : products_) {
            products.add(new Product(x.getId(), x.getName(), x.getName(), x.getRecipe_id()));
        }
        return products;
    }
    private void visibleError(Throwable th) {
        Log.e(LOG, th.getMessage(), th);
    }
}
