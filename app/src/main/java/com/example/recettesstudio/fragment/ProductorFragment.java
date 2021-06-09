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
import com.example.recettesstudio.databinding.FragmentProductorBinding;
import com.example.recettesstudio.model.Productor;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductorFragment extends Fragment {

    private FragmentProductorBinding binding;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "name";


    // TODO: Rename and change types of parameters
    private String name;


    public ProductorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param name Parameter 1.

     * @return A new instance of fragment ProductorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductorFragment newInstance(String name) {
        ProductorFragment fragment = new ProductorFragment();
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

        Productor productor = AppDatabase.getDatabase(getContext()).productorDao().getProductor(name);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_productor, container, false);

        binding.productorName.setText(productor.getName());
        binding.productorAddress.setText(productor.getAddress());

        binding.productsList.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.productsList.setAdapter(new ProductsAdapter(this,
                AppDatabase.getDatabase(getContext()).produitDao().getProduitsByProductor(name)));

        return binding.getRoot();
    }
}