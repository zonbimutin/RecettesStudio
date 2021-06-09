package com.example.recettesstudio.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.recettesstudio.model.Categorie;
import com.example.recettesstudio.model.Produit;

import java.util.List;

@Dao
public interface ProduitDao {

    @Query("SELECT * FROM Produit")
    List<Produit> getAllProduits();

    @Query("SELECT * FROM Produit WHERE name = :name")
    Produit getProduit(String name);

    @Query("SELECT * FROM Produit WHERE categorieName = :name")
    List<Produit> getProduitsByCategories(String name);

    @Query("SELECT * FROM Produit WHERE productorName = :name")
    List<Produit> getProduitsByProductor(String name);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Produit produit);

    @Delete
    void delete(Produit produit);
}
