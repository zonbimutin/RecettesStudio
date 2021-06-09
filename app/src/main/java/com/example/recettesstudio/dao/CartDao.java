package com.example.recettesstudio.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.recettesstudio.model.Cart;
import com.example.recettesstudio.model.Recette;

import java.util.List;

@Dao
public interface CartDao {


    @Query("SELECT * FROM Cart WHERE id = 1")
    Cart getCart();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Cart cart);

    @Delete
    void delete(Cart cart);
}
