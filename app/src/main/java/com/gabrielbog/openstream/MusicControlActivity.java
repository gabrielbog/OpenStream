package com.gabrielbog.openstream;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.gabrielbog.openstream.models.MusicModel;

public class MusicControlActivity extends AppCompatActivity {

    //data transfered from the other activity
    private MusicModel currentMusic;

    //ui elements
    private TextView title;
    private TextView author;
    //todo

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_control);

        //get the intent
        Intent intent = getIntent();
        currentMusic = (MusicModel) intent.getSerializableExtra("MUSIC_DATA");

        //update the ui
        TextView title = (TextView) findViewById(R.id.mcMusicTitle);
        TextView author = (TextView) findViewById(R.id.mcMusicAuthor);

        title.setText(currentMusic.getTitle());
        author.setText(currentMusic.getAuthor());
    }
}