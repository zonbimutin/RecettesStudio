package com.example.recettesstudio.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.recettesstudio.R;
import com.example.recettesstudio.adapter.RecettesAdapter;
import com.example.recettesstudio.dao.AppDatabase;
import com.example.recettesstudio.dao.RecetteDao;
import com.example.recettesstudio.databinding.FragmentRecettesBinding;
import com.example.recettesstudio.model.Recette;
import com.example.recettesstudio.model.Wishlist;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WishlistFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WishlistFragment extends Fragment {

    private FragmentRecettesBinding binding;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public WishlistFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WishlistFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WishlistFragment newInstance(String param1, String param2) {
        WishlistFragment fragment = new WishlistFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        List<Wishlist> wishlist = AppDatabase.getDatabase(getContext()).wishlistDao().getWishlist();

        ArrayList<Recette> recettes = new ArrayList<>();
        RecetteDao recetteDao = AppDatabase.getDatabase(getContext()).recetteDao();
        for(Wishlist item : wishlist){
            recettes.add(recetteDao.getRecette(item.getRecetteName()));
        }

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recettes, container, false);

        binding.recettesList.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recettesList.setAdapter(new RecettesAdapter(this, recettes));

        return binding.getRoot();
    }
}