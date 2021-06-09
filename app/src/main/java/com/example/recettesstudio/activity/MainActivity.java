package com.example.recettesstudio.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.recettesstudio.R;
import com.example.recettesstudio.dao.AppDatabase;
import com.example.recettesstudio.dao.CategorieDao;
import com.example.recettesstudio.dao.ProduitDao;
import com.example.recettesstudio.dao.RecetteDao;
import com.example.recettesstudio.databinding.ActivityMainBinding;

import com.example.recettesstudio.fragment.CartFragment;
import com.example.recettesstudio.fragment.CatalogueFragment;
import com.example.recettesstudio.fragment.CategorieFragment;
import com.example.recettesstudio.fragment.HomeFragment;
import com.example.recettesstudio.fragment.MapsFragment;
import com.example.recettesstudio.fragment.ProfileFragment;
import com.example.recettesstudio.fragment.RecettesFragment;
import com.example.recettesstudio.model.Cart;
import com.example.recettesstudio.model.Categorie;
import com.example.recettesstudio.model.Productor;
import com.example.recettesstudio.model.Produit;
import com.example.recettesstudio.model.Recette;
import com.example.recettesstudio.utils.ActivityUtils;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Cart cart = new Cart(new ArrayList<>());
        AppDatabase.getDatabase(this).cartDao().insert(cart);

        // Create Data
        ArrayList<Categorie> categories = new ArrayList<Categorie>();
        categories.add(new Categorie("fromages"));
        categories.add(new Categorie("viandes"));
        categories.add(new Categorie("boissons"));
        categories.add(new Categorie("legumes"));
        categories.add(new Categorie("fruits"));
        categories.add(new Categorie("poissons"));
        categories.add(new Categorie("vins"));
        categories.add(new Categorie("bieres"));

        CategorieDao categorieDao = AppDatabase.getDatabase(this).categorieDao();
        for (Categorie categorie : categories) {
            categorieDao.insert(categorie);
        }

        Productor productor = new Productor("la ferme du beaufort", "33 rue de la plaine", 46.1782637, 6.3005512);
        Productor productor1 = new Productor("la ferme du vin", "33 rue de la plaine", 45.8983737, 6.1225798);
        Productor productor2 = new Productor("la ferme du tous", "33 rue de la plaine", 45.9111016, 6.1328525);
        AppDatabase.getDatabase(this).productorDao().insert(productor);
        AppDatabase.getDatabase(this).productorDao().insert(productor1);
        AppDatabase.getDatabase(this).productorDao().insert(productor2);


        ArrayList<Produit> produits = new ArrayList<Produit>();
        produits.add(new Produit("beaufort", 6, "fromages", productor.getName()));
        produits.add(new Produit("reblochone", 4, "fromages", productor.getName()));
        produits.add(new Produit("filet mignon", 15, "viandes", productor.getName()));
        produits.add(new Produit("thon", 5, "poissons", productor1.getName()));
        produits.add(new Produit("coca cola", 2, "boissons", productor1.getName()));
        produits.add(new Produit("rivela", 2, "boissons", productor1.getName()));
        produits.add(new Produit("tomates", 1, "legumes", productor2.getName()));
        produits.add(new Produit("salade", 1, "legumes", productor2.getName()));
        produits.add(new Produit("bannana", 2, "fruits", productor2.getName()));
        produits.add(new Produit("melon", 4, "fruits", productor.getName()));
        produits.add(new Produit("gato negro", 5, "vins", productor1.getName()));
        produits.add(new Produit("sangre de toro", 6, "vins", productor2.getName()));
        produits.add(new Produit("goudale ipa", 3, "bieres", productor.getName()));

        ProduitDao produitDao = AppDatabase.getDatabase(this).produitDao();
        for (Produit produit : produits) {
            produitDao.insert(produit);

        }



        RecetteDao recetteDao = AppDatabase.getDatabase(this).recetteDao();

        recetteDao.insert(new Recette("ajiaco", new ArrayList<String>() {{
            add("beaufort");
            add("rivela");
            add("melon");
        }}));

        recetteDao.insert(new Recette("bandeja paisa", new ArrayList<String>() {{
            add("tomates");
            add("filet mignon");
            add("reblochone");
            add("thon");
        }}));


        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);


        ActivityUtils.addFragmentToActivity(MainActivity.this, new HomeFragment(),
                R.id.main_fragment_container);

        binding.mainCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.addFragmentToActivity(MainActivity.this, new CartFragment(),
                        R.id.main_fragment_container);
            }
        });


        binding.mainBottomNav.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        if (item.getItemId() == R.id.home) {
                            ActivityUtils.addFragmentToActivity(MainActivity.this, new HomeFragment(),
                                    R.id.main_fragment_container);
                        }  else if (item.getItemId() == R.id.recettes) {
                            ActivityUtils.addFragmentToActivity(MainActivity.this, new RecettesFragment(),
                                    R.id.main_fragment_container);
                        }  else if (item.getItemId() == R.id.map) {
                            ActivityUtils.addFragmentToActivity(MainActivity.this, new MapsFragment(),
                                    R.id.main_fragment_container);
                        }  else if (item.getItemId() == R.id.profile) {
                            ActivityUtils.addFragmentToActivity(MainActivity.this, new ProfileFragment(),
                                    R.id.main_fragment_container);
                        } else if (item.getItemId() == R.id.catalogue) {
                            ActivityUtils.addFragmentToActivity(MainActivity.this, new CategorieFragment(),
                                    R.id.main_fragment_container);
                        }
                        return true;
                    }
                }
        );
    }
}