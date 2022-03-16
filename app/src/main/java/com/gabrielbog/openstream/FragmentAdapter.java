package com.gabrielbog.openstream;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FragmentAdapter extends FragmentStateAdapter {

    private MusicListFragment f1;
    private FavoriteMusicListFragment f2;

    public FragmentAdapter(FragmentActivity fa) {
        super(fa);
    }

    @Override
    public Fragment createFragment(int position) {

        if (position == 0) {
            f1 = new MusicListFragment();
            return  f1;
        } else if (position == 1)
            f2 = new FavoriteMusicListFragment();
            return f2;
        /* (else) */
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
