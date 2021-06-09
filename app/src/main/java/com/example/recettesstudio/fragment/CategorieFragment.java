package com.example.recettesstudio.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.recettesstudio.R;
import com.example.recettesstudio.adapter.CategoriesAdapter;
import com.example.recettesstudio.dao.AppDatabase;
import com.example.recettesstudio.databinding.FragmentCategoriesBinding;


public class CategorieFragment extends Fragment {

    private FragmentCategoriesBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_categories, container, false);

        binding.categorieList.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.categorieList.setAdapter(new CategoriesAdapter(this,
                AppDatabase.getDatabase(getContext()).categorieDao().getAllCategories()));

        return binding.getRoot();
    }

}