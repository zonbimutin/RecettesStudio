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
import com.example.recettesstudio.adapter.RecettesAdapter;
import com.example.recettesstudio.dao.AppDatabase;
import com.example.recettesstudio.databinding.FragmentRecettesBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecettesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecettesFragment extends Fragment {

    private FragmentRecettesBinding binding;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RecettesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecettesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecettesFragment newInstance(String param1, String param2) {
        RecettesFragment fragment = new RecettesFragment();
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

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recettes, container, false);

        binding.recettesList.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recettesList.setAdapter(new RecettesAdapter(this,
                AppDatabase.getDatabase(getContext()).recetteDao().getAllRecettes()));

        return binding.getRoot();
    }
}