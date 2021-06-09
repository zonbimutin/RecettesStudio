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
import com.example.recettesstudio.fragment.ProductDetailFragment;
import com.example.recettesstudio.fragment.RecetteDetailFragment;
import com.example.recettesstudio.model.Produit;
import com.example.recettesstudio.model.Recette;
import com.example.recettesstudio.utils.ActivityUtils;

import java.util.List;

public class RecettesAdapter extends RecyclerView.Adapter<RecettesAdapter.PlayerHolder> {

    private final Fragment fragment;
    private final List<Recette> recettes;

    public RecettesAdapter(Fragment fragment, List<Recette> recettes) {
        this.fragment = fragment;
        this.recettes = recettes;
    }

    @NonNull
    @Override
    public PlayerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.fragment_recettes_cell, parent, false);
        return new PlayerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerHolder holder, int position) {
        Recette recette = recettes.get(position);
        holder.name.setText(recette.getName());
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.addFragmentToActivity((AppCompatActivity) fragment.getActivity(), RecetteDetailFragment.newInstance(recette.getName()),
                        R.id.recettes_container, true);
            }
        });
    }

    @Override
    public int getItemCount() {
        return recettes.size();
    }

    class PlayerHolder extends RecyclerView.ViewHolder {

        public LinearLayout container;
        public TextView name;
        public ImageView image;

        PlayerHolder(View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.recette_cell);
            name = itemView.findViewById(R.id.recette_cell_name);
            image = itemView.findViewById(R.id.recette_cell_image);


        }
    }
}
