package msk.android.academy.javatemplate.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {RecipeEntity.class, ProductEntity.class}, version = 1)
public abstract class RecipeDatabase extends RoomDatabase {
    private static RecipeDatabase singleton;

    private static final String DATABASE_NAME = "DBrecipe.db";

    public abstract RecipeDAO recipeDAO();

    public abstract ProductDAO productDAO();

    public static RecipeDatabase getAppDatabase(Context context) {
        if (singleton == null) {
            synchronized (RecipeDatabase.class) {
                if (singleton == null) {
                    singleton = Room.databaseBuilder(context.getApplicationContext(),
                            RecipeDatabase.class,
                            DATABASE_NAME)
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return singleton;
    }

}
