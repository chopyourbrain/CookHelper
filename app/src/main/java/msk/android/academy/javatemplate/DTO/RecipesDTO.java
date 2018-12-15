package msk.android.academy.javatemplate.DTO;

import com.google.gson.annotations.SerializedName;



public class RecipesDTO {
    @SerializedName("label")
    private String label;

    @SerializedName("url")
    private String url;

    @SerializedName("image")
    private String img;

    @SerializedName("ingredientLines")
    private String[] ingr;

    @SerializedName("yield")
    private int yield;

    public String getImg() {
        return img;
    }

    public int getYield() {
        return yield;
    }

    public String[] getIngr() {
        return ingr;
    }

    public String getLabel() {
        return label;
    }

    public String getUrl() {
        return url;
    }

    public RecipesItem convertToItems() {
        RecipesItem item=new RecipesItem(this.getLabel(),this.getUrl());

        return item;
    }
}
