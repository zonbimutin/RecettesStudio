package com.example.recettesstudio.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.recettesstudio.model.Recette;
import com.example.recettesstudio.model.Wishlist;

import java.util.List;

@Dao
public interface WishlistDao {

    @Query("SELECT * FROM Wishlist")
    List<Wishlist> getWishlist();


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Wishlist wishlist);

    @Delete
    void delete(Wishlist wishlist);
}
