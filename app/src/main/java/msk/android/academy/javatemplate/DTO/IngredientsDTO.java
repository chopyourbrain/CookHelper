package msk.android.academy.javatemplate.DTO;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class IngredientsDTO {
    @SerializedName("text")
    private String text;

    public String getData()
    {
        return text;
    }

}
