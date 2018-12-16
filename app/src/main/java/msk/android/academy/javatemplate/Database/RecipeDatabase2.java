package msk.android.academy.javatemplate.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {RecipeEntity.class, ProductEntity.class}, version = 1)
public abstract class RecipeDatabase2 extends RoomDatabase {
    private static RecipeDatabase2 singleton;

    private static final String DATABASE_NAME = "DBNews3.db";

    public abstract RecipeDAO recipeDAO();

    public abstract ProductDAO productDAO();

    public static RecipeDatabase2 getAppDatabase(Context context) {
        if (singleton == null) {
            synchronized (RecipeDatabase2.class) {
                if (singleton == null) {
                    singleton = Room.databaseBuilder(context.getApplicationContext(),
                            RecipeDatabase2.class,
                            DATABASE_NAME)
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return singleton;
    }

}
