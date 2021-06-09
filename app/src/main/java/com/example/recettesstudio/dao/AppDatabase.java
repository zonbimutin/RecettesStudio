package com.example.recettesstudio.dao;

import android.content.Context;

import androidx.databinding.adapters.Converters;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.example.recettesstudio.model.Cart;
import com.example.recettesstudio.model.Categorie;
import com.example.recettesstudio.model.Productor;
import com.example.recettesstudio.model.Produit;
import com.example.recettesstudio.model.Recette;
import com.example.recettesstudio.model.Wishlist;
import com.example.recettesstudio.utils.Converter;

@Database(entities = {
        Productor.class,
        Categorie.class,
        Produit.class,
        Recette.class,
        Wishlist.class,
        Cart.class
}, version = AppDatabase.DATABASE_VERSION, exportSchema = false)

@TypeConverters({Converter.class})

public abstract class AppDatabase extends RoomDatabase {

    public static final int DATABASE_VERSION = 7;
    public static final String DATABASE_NAME = "myDbb";

    public abstract ProductorDao productorDao();
    public abstract CategorieDao categorieDao();
    public abstract ProduitDao produitDao();
    public abstract RecetteDao recetteDao();
    public abstract WishlistDao wishlistDao();
    public abstract CartDao cartDao();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME)
                            .fallbackToDestructiveMigration().allowMainThreadQueries().build();
                }
            }
        }
        return INSTANCE;
    }
}