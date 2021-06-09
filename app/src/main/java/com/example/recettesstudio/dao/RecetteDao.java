package com.example.recettesstudio.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.recettesstudio.model.Produit;
import com.example.recettesstudio.model.Recette;

import java.util.List;

@Dao
public interface RecetteDao {

    @Query("SELECT * FROM recette")
    List<Recette> getAllRecettes();

    @Query("SELECT * FROM Recette WHERE name = :name")
    Recette getRecette(String name);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Recette recette);

    @Delete
    void delete(Recette recette);
}
