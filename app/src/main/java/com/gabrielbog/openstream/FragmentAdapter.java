package com.gabrielbog.openstream;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FragmentAdapter extends FragmentStateAdapter {

    private String currentUser = "";

    private MusicListFragment f1;
    private FavoriteMusicListFragment f2;
    private OptionsFragment f3;

    public FragmentAdapter(FragmentActivity fa, String currentUser) {
        super(fa);
        this.currentUser = currentUser;
    }

    @Override
    public Fragment createFragment(int position) {

        if (position == 0) {
            return MusicListFragment.newInstance(currentUser);
        } else if (position == 1){
            return FavoriteMusicListFragment.newInstance(currentUser);
        } else {
            return OptionsFragment.newInstance(currentUser);
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
