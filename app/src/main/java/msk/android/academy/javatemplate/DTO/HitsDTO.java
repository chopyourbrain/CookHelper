package msk.android.academy.javatemplate.DTO;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HitsDTO{

    private RecipesDTO recipe;

    public RecipesDTO getData()
    {
        return recipe;
    }

}
