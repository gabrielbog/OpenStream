package com.gabrielbog.openstream;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.gabrielbog.openstream.models.MusicModel;
import com.gabrielbog.openstream.services.MediaPlayerService;

public class MusicControlActivity extends AppCompatActivity {

    //data transfered from the other activity
    private MusicModel currentMusic;

    //service stuff
    private MusicListArrays musicListInstance;
    private String musicPath = Environment.getExternalStorageDirectory().getPath() +  "/temp.mp3";
    private Intent service;

    private MediaPlayerService mediaPlayerService;
    private Boolean bound = false;

    //seekbar control
    private Handler handler;

    //ui elements
    private TextView title;
    private TextView author;

    private TextView currentPlaytime;
    private TextView totalPlaytime;

    private ImageView mcStopButton;
    private ImageView mcPauseButton;

    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_control);

        //get the intent
        Intent intent = getIntent();
        currentMusic = (MusicModel) intent.getSerializableExtra("MUSIC_DATA");

        //get music array list for isPlaying variable
        musicListInstance = MusicListArrays.getInstance();

        //prepare service
        service = new Intent(this, MediaPlayerService.class);
        service.putExtra("MUSIC_PATH", musicPath);
        bindService(service, connection, Context.BIND_AUTO_CREATE);
        startService(service);

        //update the ui
        TextView title = (TextView) findViewById(R.id.mcMusicTitle);
        TextView author = (TextView) findViewById(R.id.mcMusicAuthor);

        TextView currentPlaytime = (TextView) findViewById(R.id.textViewPlayTime);
        TextView totalPlaytime = (TextView) findViewById(R.id.textViewTotalTime);

        ImageView mcStopButton = (ImageView) findViewById(R.id.mc_stop_button);
        ImageView mcPauseButton = (ImageView) findViewById(R.id.mc_pause_button);

        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);

        title.setText(currentMusic.getTitle());
        author.setText(currentMusic.getAuthor());

        mcPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //play music
                if(!currentMusic.getLink().equals("")) {

                    if(mediaPlayerService.getPlayState() == false) {
                        mediaPlayerService.playMusic();
                        mcPauseButton.setImageResource(R.drawable.ic_baseline_pause_24);
                    }
                    else {
                        mediaPlayerService.pauseMusic();
                        mcPauseButton.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                    }

                }
            }
        });

        mcStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!currentMusic.getLink().equals("")) {

                    if(mediaPlayerService.getPlayState() == true) {
                        mediaPlayerService.stopMusic();
                        mcPauseButton.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                    }
                }
            }
        });

        //seekbar updating stuff
        handler = new Handler();
    }

    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {

            MediaPlayerService.LocalBinder binder = (MediaPlayerService.LocalBinder) service;
            mediaPlayerService = binder.getService();
            bound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            bound = false;
        }
    };

    private void updateSeekBar() {
        int currentTime = mediaPlayerService.currentTime();
        seekBar.setProgress(currentTime);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                updateSeekBar();
            }
        };
        handler.postDelayed(runnable, 1000);
    }
}