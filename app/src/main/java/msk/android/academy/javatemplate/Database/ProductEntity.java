package msk.android.academy.javatemplate.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@android.arch.persistence.room.Entity(tableName = "products")
public class ProductEntity {

    public ProductEntity(){}

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(int recipe_id) {
        this.recipe_id = recipe_id;
    }

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "recipe_id")
    private int recipe_id;

    public float getCount() {
        return count;
    }

    public void setCount(float count) {
        this.count = count;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public boolean isCheched() {
        return cheched;
    }

    public void setCheched(boolean cheched) {
        this.cheched = cheched;
    }

    @ColumnInfo(name = "count")
    private float count;

    @ColumnInfo(name = "balance")
    private float balance;

    @ColumnInfo(name = "checked")
    private boolean cheched;


}
