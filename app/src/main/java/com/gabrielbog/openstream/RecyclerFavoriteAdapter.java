package com.gabrielbog.openstream;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gabrielbog.openstream.models.MusicModel;

import java.util.ArrayList;

public class RecyclerFavoriteAdapter extends RecyclerView.Adapter<RecyclerFavoriteAdapter.MyViewHolder> {

    private MusicListArrays favoriteMusicListInstance;
    private ArrayList<MusicModel> favoriteMusicList;

    public RecyclerFavoriteAdapter() {
        favoriteMusicListInstance = MusicListArrays.getInstance();
        favoriteMusicList = favoriteMusicListInstance.getFavoriteArray();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView albumCover;
        private TextView musicTitle;
        private TextView musicAuthor;

        public MyViewHolder(final View view) {
            super(view);
            albumCover = view.findViewById(R.id.albumCover);
            musicTitle = view.findViewById(R.id.musicTitle);
            musicAuthor = view.findViewById(R.id.musicAuthor);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_favorite_music, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String title = favoriteMusicListInstance.getFavoriteArrayElement(position).getTitle();
        String author = favoriteMusicListInstance.getFavoriteArrayElement(position).getAuthor();
        String link = favoriteMusicListInstance.getFavoriteArrayElement(position).getLink();

        //set image somehow
        holder.musicTitle.setText(title);
        holder.musicAuthor.setText(author);

        //add recycler element listener for starting music

        //add hold listener

        //updating the list
        if(favoriteMusicListInstance.getFavoriteNotifier() == 0)
        {
            favoriteMusicList.clear();
            favoriteMusicList.addAll(favoriteMusicListInstance.getFavoriteMusicList());
            favoriteMusicListInstance.setFavoriteMusicList(favoriteMusicList);
            favoriteMusicListInstance.setFavoriteNotifier(-1);
            this.notifyItemInserted(favoriteMusicListInstance.getFavoritePosition());
        }
        else if(favoriteMusicListInstance.getFavoriteNotifier() == 1)
        {
            favoriteMusicList.clear();
            favoriteMusicList.addAll(favoriteMusicListInstance.getFavoriteMusicList());
            favoriteMusicListInstance.setFavoriteMusicList(favoriteMusicList);
            favoriteMusicListInstance.setFavoriteNotifier(-1);
            this.notifyItemRemoved(favoriteMusicListInstance.getFavoritePosition());
        }
    }

    @Override
    public int getItemCount() {
        return favoriteMusicListInstance.getFavoriteArraySize();
    }
}
