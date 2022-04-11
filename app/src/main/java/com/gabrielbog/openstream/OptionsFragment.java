package com.gabrielbog.openstream;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class OptionsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private String currentUser = "";

    private TextView greet;

    public OptionsFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static OptionsFragment newInstance(String currentUser) {
        OptionsFragment fragment = new OptionsFragment();
        Bundle args = new Bundle();
        args.putString("USERNAME", currentUser);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.currentUser = getArguments().getString("USERNAME");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_options, container, false);

        greet = view.findViewById(R.id.usernameGreet);
        greet.setText("Welcome, " + currentUser + "!");

        return view;
    }
}