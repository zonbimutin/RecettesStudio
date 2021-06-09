package com.example.recettesstudio.model;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.recettesstudio.dao.AppDatabase;

import java.util.ArrayList;

@Entity
public class Wishlist {



    @NonNull
    @PrimaryKey
    private String recetteName;


    public Wishlist(String recetteName){
        setRecetteName(recetteName);

    }

    @NonNull
    public String getRecetteName() {
        return recetteName;
    }

    public void setRecetteName(@NonNull String recetteName) {
        this.recetteName = recetteName;
    }

    public Recette getRecette(Context context){
        return AppDatabase.getDatabase(context).recetteDao().getRecette(recetteName);
    }


}
