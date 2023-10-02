package com.example.exampractice;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CategoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoryFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CategoryFragment() {
        // Required empty public constructor
    }
    private GridView catView;
    public static List<CategoryModel> catList=new ArrayList<>();
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CategoryFragment.
     */
    // TODO: Rename and change types and number of parameters


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_category, container, false);
        catView=view.findViewById(R.id.cat_Grid);
        loadCategories();
        CategoryAdapter adapter=new CategoryAdapter(catList);
        catView.setAdapter(adapter);
        return view;
    }
    private void loadCategories()
    {
        catList.clear();
        catList.add(new CategoryModel("1","QUANTITATIVE APTITUDE",25));
        catList.add(new CategoryModel("1","LOGICAL REASONING",25));
        catList.add(new CategoryModel("1","VERBAL ABILITY",25));
        catList.add(new CategoryModel("1","PROGRAMMING",25));
    }
}
