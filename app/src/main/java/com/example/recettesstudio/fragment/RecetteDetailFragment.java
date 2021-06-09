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
import com.example.recettesstudio.dao.CartDao;
import com.example.recettesstudio.dao.ProduitDao;
import com.example.recettesstudio.databinding.FragmentRecetteDetailBinding;
import com.example.recettesstudio.model.Cart;
import com.example.recettesstudio.model.Productor;
import com.example.recettesstudio.model.Produit;
import com.example.recettesstudio.model.Recette;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecetteDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecetteDetailFragment extends Fragment {

    private FragmentRecetteDetailBinding binding;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";


    // TODO: Rename and change types of parameters
    private String recetteName;


    public RecetteDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param recetteName Parameter 1.
     * @return A new instance of fragment RecetteDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecetteDetailFragment newInstance(String recetteName) {
        RecetteDetailFragment fragment = new RecetteDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, recetteName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            recetteName = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        Recette recette = AppDatabase.getDatabase(getContext()).recetteDao().getRecette(recetteName);

        ArrayList<Produit> produits = new ArrayList<>();
        ProduitDao produitDao = AppDatabase.getDatabase(getContext()).produitDao();

        for(String name : recette.getProductsName()){
            produits.add(produitDao.getProduit(name));
        }

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recette_detail, container, false);

        binding.recetteName.setText(recette.getName());

        binding.productsList.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.productsList.setAdapter(new ProductsAdapter(this, (List<Produit>) produits, false));

        binding.addAllToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartDao cartDao = AppDatabase.getDatabase(getContext()).cartDao();
                Cart cart = cartDao.getCart();

                ArrayList<String> cartItems = cart.getProductsName();

                for(Produit produit : produits){
                    cartItems.add(produit.getName());
                }
                cart.setProductsName(cartItems);
                cartDao.insert(cart);
            }
        });

        return binding.getRoot();
    }
}