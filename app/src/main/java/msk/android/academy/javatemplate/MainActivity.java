package msk.android.academy.javatemplate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import msk.android.academy.javatemplate.DTO.RecipesDTO;
import msk.android.academy.javatemplate.DTO.RecipesResponse;
import msk.android.academy.javatemplate.Database.RecipeDatabase;
import msk.android.academy.javatemplate.Database.RecipeEntity;
import msk.android.academy.javatemplate.NET.Network;

public class MainActivity extends AppCompatActivity {
    RecipeDatabase db;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = RecipeDatabase.getAppDatabase(this);
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
        List<RecipesDTO> listdto = response.getData();
        List<RecipeEntity> recipes = new ArrayList<RecipeEntity>();
        int i = 0;
        for (RecipesDTO x : listdto) {
            RecipeEntity item = new RecipeEntity(x.getLabel(), x.getImg(), x.getYield(),x.getUrl());
            recipes.add(item);
        }
        return recipes.toArray(new RecipeEntity[recipes.size()]);
    }

    public void saveRecipes(RecipeEntity[] recipes) {
        db.recipeDAO().deleteAll();
        db.recipeDAO().insertAll(recipes);
    }
}
