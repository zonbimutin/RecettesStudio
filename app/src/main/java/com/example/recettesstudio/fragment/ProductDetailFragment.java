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
import com.example.recettesstudio.databinding.FragmentProductDetailBinding;
import com.example.recettesstudio.model.Cart;
import com.example.recettesstudio.model.Productor;
import com.example.recettesstudio.model.Produit;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductDetailFragment extends Fragment {

    private FragmentProductDetailBinding binding;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";


    // TODO: Rename and change types of parameters
    private String name;


    public ProductDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param name Parameter 1.
     * @return A new instance of fragment ProductDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductDetailFragment newInstance(String name) {
        ProductDetailFragment fragment = new ProductDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, name);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            name = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Produit produit = AppDatabase.getDatabase(getContext()).produitDao().getProduit(name);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_product_detail, container, false);

        binding.productName.setText(produit.getName());
        binding.productProductor.setText(produit.getProductorName());
        binding.productPrice.setText(String.valueOf(produit.getPrice()));

        binding.productAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CartDao cartDao = AppDatabase.getDatabase(getContext()).cartDao();
                Cart cart = cartDao.getCart();

                ArrayList<String> cartItems = cart.getProductsName();
                cartItems.add(produit.getName());

                cart.setProductsName(cartItems);
                cartDao.insert(cart);


            }
        });


        return binding.getRoot();
    }
}