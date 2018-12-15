package msk.android.academy.javatemplate.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Observable;

@Dao
public interface RecipeDAO {

    @Query("SELECT * FROM recipes")
    Observable<List<RecipeEntity>> getAll();

    @Query("SELECT * FROM recipes WHERE (id = :myid)")
    Observable<RecipeEntity> getById(int myid);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(RecipeEntity... recipes);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(RecipeEntity recipes);

    @Delete
    void delete(RecipeEntity recipes);

    @Query("DELETE FROM recipes")
    void deleteAll();

    @Query("SELECT * FROM products WHERE (recipe_id = :myid)")
    Observable<List<ProductEntity>> getProducts(int myid);
}
