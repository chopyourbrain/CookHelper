package msk.android.academy.javatemplate.DTO;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class IngredientsDTO {
    @SerializedName("text")
    private String text;
    @SerializedName("weight")
    private float weight;

    public String getText()
    {
        return text;
    }
    public float getWeight()
    {
        return weight;
    }

}
