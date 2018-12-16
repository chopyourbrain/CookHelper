package msk.android.academy.javatemplate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import msk.android.academy.javatemplate.DTO.HitsDTO;
import msk.android.academy.javatemplate.DTO.RecipesDTO;
import msk.android.academy.javatemplate.DTO.RecipesResponse;
import msk.android.academy.javatemplate.Database.RecipeDatabase;
import msk.android.academy.javatemplate.Database.RecipeEntity;
import msk.android.academy.javatemplate.Dish.DishActivity;
import msk.android.academy.javatemplate.NET.Network;
import msk.android.academy.javatemplate.NewDish.NewDishActivity;
import msk.android.academy.javatemplate.Product.Product;
import msk.android.academy.javatemplate.Product.ProductActivity;

public class MainActivity extends AppCompatActivity {
    RecipeDatabase db;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CardView cd=findViewById(R.id.card1);
        CardView cd2=findViewById(R.id.card2);
        cd.setOnClickListener(v->
        {
            Intent intent = new Intent(this, DishActivity.class);
            startActivity(intent);
            Animatoo.animateShrink(this);
        });
        cd2.setOnClickListener(v->
        {
            Intent intent = new Intent(this, ProductActivity.class);
            startActivity(intent);
            Animatoo.animateShrink(this);
        });
    }

}
