package com.gabrielbog.openstream;

import android.util.Log;
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

    public interface ButtonClickListener {
        void onButtonClick(View view, Button button, int position);
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, RecyclerView recyclerView, int position);
    }

    private MusicListArrays musicListInstance;
    private ButtonClickListener buttonClickListener;
    private ItemClickListener itemClickListener;

    public RecyclerAdapter() {
        musicListInstance = MusicListArrays.getInstance();
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

            markButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (buttonClickListener != null) buttonClickListener.onButtonClick(view, markButton, getAdapterPosition());
                }
            });
        }
    }

    // allows clicks events to be caught
    void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    // allows clicks events to be caught
    void setButtonClickListener(ButtonClickListener buttonClickListener) {
        this.buttonClickListener = buttonClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_music, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String title = musicListInstance.getNormalArrayElement(position).getTitle();
        String author = musicListInstance.getNormalArrayElement(position).getAuthor();
        String link = musicListInstance.getNormalArrayElement(position).getLink();

        //set image somehow
        holder.musicTitle.setText(title);
        holder.musicAuthor.setText(author);

        //add button touch listener to copy to the favorite fragment's arraylist

        //add recycler element listener for starting music

        //add listener for holding element
    }

    @Override
    public int getItemCount() {
        return musicListInstance.getNormalArraySize();
    }

    //might be a good idea to make a reload array function
}