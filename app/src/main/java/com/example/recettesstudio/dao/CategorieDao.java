package com.example.recettesstudio.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.recettesstudio.model.Categorie;
import com.example.recettesstudio.model.Productor;

import java.util.List;

@Dao
public interface CategorieDao {

    @Query("SELECT * FROM Categorie")
    List<Categorie> getAllCategories();

    @Query("SELECT * FROM Categorie WHERE name = :name")
    Categorie getCategorie(String name);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Categorie categorie);

    @Delete
    void delete(Categorie categorie);
}
