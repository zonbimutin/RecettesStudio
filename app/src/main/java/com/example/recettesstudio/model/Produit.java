package com.example.recettesstudio.model;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.recettesstudio.dao.AppDatabase;

@Entity
public class Produit {

    @NonNull
    @PrimaryKey
    private String name;
    private String picture;
    private int price;
    private String categorieName;
    private String productorName;

    public Produit(String name, int price, String categorieName, String productorName){
        setName(name);
        setPrice(price);
        setCategorieName(categorieName);
        setProductorName(productorName);
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCategorieName() {
        return categorieName;
    }


    public Categorie getCategorie(Context context) {
        return AppDatabase.getDatabase(context).categorieDao().getCategorie(categorieName);
    }

    public void setCategorieName(String categorieName) {
        this.categorieName = categorieName;
    }
    public String getProductorName() {
        return productorName;
    }

    public Productor getProductor(Context context) {
        return AppDatabase.getDatabase(context).productorDao().getProductor(productorName);
    }

    public void setProductorName(String productorName) {
        this.productorName = productorName;
    }

}
