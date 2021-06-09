package com.example.recettesstudio.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.recettesstudio.model.Productor;

import java.util.List;

@Dao
public interface ProductorDao {

    @Query("SELECT * FROM Productor")
    List<Productor> getAllProductors();

    @Query("SELECT * FROM Productor WHERE name = :name")
    Productor getProductor(String name);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Productor productor);

    @Delete
    void delete(Productor productor);
}
