package com.example.recettesstudio.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.recettesstudio.R;
import com.example.recettesstudio.adapter.ProductsAdapter;
import com.example.recettesstudio.dao.AppDatabase;
import com.example.recettesstudio.dao.ProduitDao;
import com.example.recettesstudio.databinding.FragmentCartBinding;
import com.example.recettesstudio.model.Cart;
import com.example.recettesstudio.model.Produit;
import com.example.recettesstudio.model.Recette;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends Fragment {

    FragmentCartBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Cart cart = AppDatabase.getDatabase(getContext()).cartDao().getCart();
        int total = 0;

        ArrayList<Produit> produits = cart.getProducts(getContext());

        for(Produit produit : produits){
            total += produit.getPrice();
        }

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart, container, false);

        binding.cartTotal.setText(String.valueOf(total) + "€");
        binding.productsList.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.productsList.setAdapter(new ProductsAdapter(this, (List<Produit>) produits));

        return binding.getRoot();
    }

}