package msk.android.academy.javatemplate.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

@Dao
public interface ProductDAO {

    @Query("SELECT * FROM products")
    Single<List<ProductEntity>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(ProductEntity... products);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ProductEntity products);

    @Delete
    void delete(ProductEntity products);

    @Query("DELETE FROM products")
    void deleteAll();

    @Query("SELECT * FROM products WHERE recipe_id = :url")
    List<ProductEntity> getById(String url);
}
