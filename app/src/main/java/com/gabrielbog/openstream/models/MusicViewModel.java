package com.gabrielbog.openstream.models;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MusicViewModel extends ViewModel {
    private MutableLiveData<MusicModel> music = new MutableLiveData<>();

    public MutableLiveData<MusicModel> getMusic() {
        return music;
    }
}
