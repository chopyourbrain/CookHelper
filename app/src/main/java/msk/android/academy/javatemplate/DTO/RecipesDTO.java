package msk.android.academy.javatemplate.DTO;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

public class RecipesDTO {
    @SerializedName("label")
    private String label;

    @SerializedName("url")
    private String url;

    @SerializedName("image")
    private String image;

    //@SerializedName("ingredientLines")
    //private String[] ingr;

    @SerializedName("yield")
    private int yield;

    public String getImage() {
        return image;
    }

    public int getYield() {
        return yield;
    }

//    public String[] getIngr() {
//        Log.d("MYLOG","ingr = "+ingr);
//        return ingr;
//    }

    public String getLabel() {
        Log.d("MYLOG","Lable = "+label);
//        Log.d("MYLOG","ingr = "+ingr);
        return label;
    }

    public String getUrl() {
        return url;
    }
}
