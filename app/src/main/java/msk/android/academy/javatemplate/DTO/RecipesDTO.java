package msk.android.academy.javatemplate.DTO;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RecipesDTO {
    @SerializedName("label")
    private String label;

    @SerializedName("url")
    private String url;

    @SerializedName("image")
    private String image;
//
//    @SerializedName("ingredientLines")
    private List<IngredientsDTO> ingredients;

    @SerializedName("yield")
    private int yield;

    @SerializedName("totalTime")
    private String time;

    public String getImage() {
        return image;
    }

    public String getTime() {
        return time;
    }

    public int getYield() {
        return yield;
    }

//    public String getIngr() {
//        Log.d("MYLOG","ingr = "+ingr);
//        return ingr;
//    }

    public List<IngredientsDTO> getIngredients() {
        return ingredients;
    }

    public String getLabel() {
        Log.d("MYLOG","Lable = "+label);
        Log.d("MYLOG","ingr = "+ingredients.get(0).getText());
        return label;
    }

    public String getUrl() {
        return url;
    }
}
