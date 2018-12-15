package msk.android.academy.javatemplate.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@android.arch.persistence.room.Entity(tableName = "recipes")
public class RecipeEntity {

    public RecipeEntity(){}

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

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "lable")
    private String lable;

    @ColumnInfo(name = "image")
    private String image;

    @ColumnInfo(name = "yield")
    private int yield;

    @ColumnInfo(name = "url")
    private String url;




}
