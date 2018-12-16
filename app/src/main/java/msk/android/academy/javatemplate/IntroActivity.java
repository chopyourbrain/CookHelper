package msk.android.academy.javatemplate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import msk.android.academy.javatemplate.Dish.DishActivity;

public class IntroActivity extends AppCompatActivity {
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        Disposable disposable = Completable.complete()
                .delay(3, TimeUnit.SECONDS)
                .subscribe(this::startSecondActivity);
        compositeDisposable.add(disposable);
    }

    private void startSecondActivity() {

        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        compositeDisposable.dispose();
    }
}
