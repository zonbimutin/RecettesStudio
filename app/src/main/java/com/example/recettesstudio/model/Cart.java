package com.example.recettesstudio.model;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.recettesstudio.dao.AppDatabase;

import java.util.ArrayList;

@Entity
public class Cart {

    @NonNull
    @PrimaryKey
    private int id;
    private ArrayList<String> productsName;

    public Cart(ArrayList<String> productsName){
        setId(1);
        setProductsName(productsName);
    }


    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
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
