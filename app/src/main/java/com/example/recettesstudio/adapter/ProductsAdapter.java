package com.example.recettesstudio.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recettesstudio.R;
import com.example.recettesstudio.fragment.CategorieFragment;
import com.example.recettesstudio.fragment.ProductDetailFragment;
import com.example.recettesstudio.fragment.ProductsFragment;
import com.example.recettesstudio.model.Categorie;
import com.example.recettesstudio.model.Produit;
import com.example.recettesstudio.utils.ActivityUtils;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.PlayerHolder> {

    private final Fragment fragment;
    private final List<Produit> produits;

    public ProductsAdapter(Fragment fragment, List<Produit> produits) {
        this.fragment = fragment;
        this.produits = produits;
    }

    @NonNull
    @Override
    public PlayerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.fragment_products_cell, parent, false);
        return new PlayerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerHolder holder, int position) {
        Produit produit = produits.get(position);
        holder.name.setText(produit.getName());
        holder.productor.setText(produit.getProductorName());
        holder.price.setText(String.valueOf(produit.getPrice()));
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.addFragmentToActivity((AppCompatActivity) fragment.getActivity(), ProductDetailFragment.newInstance(produit.getName()),
                        R.id.products_container, true);
            }
        });
    }

    @Override
    public int getItemCount() {
        return produits.size();
    }

    class PlayerHolder extends RecyclerView.ViewHolder {

        public LinearLayout container;
        public TextView name;
        public TextView productor;
        public ImageView image;
        public TextView price;

        PlayerHolder(View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.product_cell);
            name = itemView.findViewById(R.id.product_cell_name);
            productor = itemView.findViewById(R.id.product_cell_productor);
            price = itemView.findViewById(R.id.product_cell_price);
            image = itemView.findViewById(R.id.product_cell_image);


        }
    }
}
