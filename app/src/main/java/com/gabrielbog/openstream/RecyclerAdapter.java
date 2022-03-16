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

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private ArrayList<MusicModel> musicList;

    public RecyclerAdapter(ArrayList<MusicModel> musicList) {
        this.musicList = musicList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView albumCover;
        private TextView musicTitle;
        private TextView musicAuthor;
        private Button markButton;

        public MyViewHolder(final View view) {
            super(view);
            albumCover = view.findViewById(R.id.albumCover);
            musicTitle = view.findViewById(R.id.musicTitle);
            musicAuthor = view.findViewById(R.id.musicAuthor);
            markButton = view.findViewById(R.id.markButton);

            //add button touch listener to copy to the favorite fragment's arraylist
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_music, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String title = musicList.get(position).getTitle();
        String author = musicList.get(position).getAuthor();
        String link = musicList.get(position).getLink();

        //set image somehow
        holder.musicTitle.setText(title);
        holder.musicAuthor.setText(author);
    }

    @Override
    public int getItemCount() {
        return musicList.size();
    }
}
