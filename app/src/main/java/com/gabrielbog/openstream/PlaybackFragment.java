package com.gabrielbog.openstream;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.gabrielbog.openstream.models.MusicModel;
import com.gabrielbog.openstream.models.MusicViewModel;

public class PlaybackFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private MusicViewModel currentMusic;

    public PlaybackFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static PlaybackFragment newInstance(String param1, String param2) {
        PlaybackFragment fragment = new PlaybackFragment();
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
        View view = inflater.inflate(R.layout.fragment_playback, container, false);

        //get viewmodel, new or existing
        MusicViewModel currentMusic = new ViewModelProvider(requireActivity()).get(MusicViewModel.class);

        //setting up the elements
        TextView title = (TextView) view.findViewById(R.id.playbackMusicTitle);
        title.setText("Select a track!");

        //checking if the current music is changed
        final Observer<MusicModel> nameObserver = new Observer<MusicModel>() {
            @Override
            public void onChanged(MusicModel musicModel) {
                if(musicModel.getLink().equals(""))
                    title.setText("Select a track!");
                else
                    title.setText(musicModel.getTitle());
            }
        };

        currentMusic.getMusic().observe(requireActivity(), nameObserver);

        //item listener to start a new activity with the elements from MusicModel
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentMusic.getMusic().getValue().getLink().equals("")) {
                    Toast.makeText(getContext(), "Select a track first!", Toast.LENGTH_LONG).show();
                }

                else {

                    MusicModel transferedData = currentMusic.getMusic().getValue();

                    Intent intent = new Intent(getActivity(), MusicControlActivity.class);
                    intent.putExtra("MUSIC_DATA", transferedData);
                    startActivity(intent);
                }
            }
        });

        return view;
    }
}