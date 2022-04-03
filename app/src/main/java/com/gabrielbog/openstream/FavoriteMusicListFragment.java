package com.gabrielbog.openstream;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gabrielbog.openstream.models.MusicModel;
import com.gabrielbog.openstream.models.MusicViewModel;

import java.util.ArrayList;

public class FavoriteMusicListFragment extends Fragment implements RecyclerFavoriteAdapter.ItemClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView;
    private MusicListArrays favoriteMusicListInstance;
    private AdapterManager adapterInstance;

    private MusicViewModel currentMusic;

    public FavoriteMusicListFragment() {
        // Required empty public constructor
    }

    public static FavoriteMusicListFragment newInstance(String param1, String param2) {
        FavoriteMusicListFragment fragment = new FavoriteMusicListFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite_music_list, container, false);

        recyclerView = view.findViewById(R.id.favoriteMusicList);
        favoriteMusicListInstance = MusicListArrays.getInstance();

        //get viewmodel, new or existing
        currentMusic = new ViewModelProvider(requireActivity()).get(MusicViewModel.class);

        MusicModel test;

        //it would be a good idea to make a loading function, would be useful for loading data after uploading new music

        /*
        test = new MusicModel("x", "y", "z");
        musicList = listInstance.getFavoriteMusicList();
        musicList.add(test);
        listInstance.setFavoriteMusicList(musicList);
        */

        //recycler adapter setup

        adapterInstance = AdapterManager.getInstance();
        adapterInstance.getRecyclerFavoriteAdapter().setItemClickListener(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapterInstance.getRecyclerFavoriteAdapter());

        return view;
    }

    //item listener action
    @Override
    public void onItemClick(View view, int position) {
        transferToPlaybackFrame(position);
    }

    private void transferToPlaybackFrame(int position) {
        MusicModel temp = favoriteMusicListInstance.getFavoriteArrayElement(position);
        currentMusic.getMusic().setValue(temp);
    }

    //holding item listener action
    @Override
    public void onItemLongClick(View view, RecyclerView recyclerView, int position) {

    }
}