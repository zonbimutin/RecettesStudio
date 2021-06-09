package com.example.recettesstudio.model;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.recettesstudio.dao.AppDatabase;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Recette {

    @NonNull
    @PrimaryKey
    private String name;
    private String picture;
    private ArrayList<String> productsName;

    public Recette(String name, ArrayList<String> productsName){
        setName(name);
        setProductsName(productsName);
    }


    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public ArrayList<String> getProductsName() {
        return productsName;
    }

    public void setProductsName(ArrayList<String> productsName) {
        this.productsName = productsName;
    }

    public ArrayList<Produit> getProducts(Context context){

        ArrayList<Produit> produits = new ArrayList<>();
        for(String name : productsName){

            Produit produit = AppDatabase.getDatabase(context).produitDao().getProduit(name);

            if(produit != null){
                produits.add(produit);
            }

        }
        return produits;

    }

}
