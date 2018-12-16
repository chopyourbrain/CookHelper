package msk.android.academy.javatemplate.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import msk.android.academy.javatemplate.DTO.RecipesDTO;

@android.arch.persistence.room.Entity(tableName = "recipes")
public class RecipeEntity {

    public RecipeEntity(String lable, String image, int yield, String url){
        this.image = image;
        this.lable = lable;
        this.yield = yield;
        this.url = url;
 //       this.time = time;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getYield() {
        return yield;
    }

    public void setYield(int yield) {
        this.yield = yield;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }



    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "lable")
    private String lable;

    @ColumnInfo(name = "image")
    private String image;

    @ColumnInfo(name = "yield")
    private int yield;

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @ColumnInfo(name = "time")
    private int time;


    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "url")
    private String url;


}
