package msk.android.academy.javatemplate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;

import msk.android.academy.javatemplate.Database.RecipeDatabase;

public class RecipeDetailsActivity extends AppCompatActivity {
    String url;
    String LOG="My_Log";
    RecipeDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        url = getIntent().getStringExtra("url");
        Log.d(LOG, url);

        db = RecipeDatabase.getAppDatabase(this);


        WebView webView = findViewById (R.id.web_view);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl(url);
    }
}
