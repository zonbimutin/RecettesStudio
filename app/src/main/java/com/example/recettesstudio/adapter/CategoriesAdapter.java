package com.example.recettesstudio.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;


import com.example.recettesstudio.R;
import com.example.recettesstudio.activity.MainActivity;
import com.example.recettesstudio.fragment.CartFragment;
import com.example.recettesstudio.fragment.CategorieFragment;
import com.example.recettesstudio.fragment.ProductsFragment;
import com.example.recettesstudio.model.Categorie;
import com.example.recettesstudio.utils.ActivityUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.PlayerHolder> {

    private final CategorieFragment fragment;
    private final List<Categorie> categories;

    public CategoriesAdapter(CategorieFragment fragment, List<Categorie> categories) {
        this.fragment = fragment;
        this.categories = categories;
    }

    @NonNull
    @Override
    public PlayerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.fragment_categories_cell, parent, false);
        return new PlayerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerHolder holder, int position) {
        Categorie categorie = categories.get(position);
        holder.name.setText(categorie.getName());
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.addFragmentToActivity((AppCompatActivity) fragment.getActivity(), ProductsFragment.newInstance(categorie.getName()),
                        R.id.categorie_container, true);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    class PlayerHolder extends RecyclerView.ViewHolder {

        public LinearLayout container;
        public TextView name;

        PlayerHolder(View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.categorie_cell);
            name = itemView.findViewById(R.id.catagorie_cell_name);

        }
    }
}
