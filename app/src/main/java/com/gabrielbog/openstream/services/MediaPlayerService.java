package com.gabrielbog.openstream.services;

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.IOException;

public class MediaPlayerService extends Service implements MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener, MediaPlayer.OnSeekCompleteListener, MediaPlayer.OnInfoListener, MediaPlayer.OnBufferingUpdateListener {

    private final IBinder serviceBinder = new LocalBinder();
    private MediaPlayer mediaPlayer = null;
    private String filePath;

    public MediaPlayerService(){

    }

    public class LocalBinder extends Binder {
        public MediaPlayerService getService() {

            // Return this instance of LocalService so clients can call public methods
            return MediaPlayerService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return serviceBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mediaPlayer = new MediaPlayer();

        mediaPlayer.setOnCompletionListener(this);
        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.setOnErrorListener(this);
        mediaPlayer.setOnSeekCompleteListener(this);
        mediaPlayer.setOnInfoListener(this);
        mediaPlayer.setOnBufferingUpdateListener(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        filePath = (String) intent.getSerializableExtra("MUSIC_PATH");
        File file = new File(filePath);
        Log.e("Exists/Read: ", file.exists() + "/" + file.canRead());

        if(!mediaPlayer.isPlaying()) {

            try {
                mediaPlayer.setDataSource(getApplicationContext(), Uri.parse(filePath));
                mediaPlayer.prepareAsync();
            }
            catch (IOException e) {

                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }

        //if we get killed, after returning from here, restart
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if(mediaPlayer != null) {
            if(mediaPlayer.isPlaying()){
                mediaPlayer.stop();
            }

            mediaPlayer.release();
        }
    }

    //called when MediaPlayer is ready
    @Override
    public void onPrepared(MediaPlayer player) {
        player.start();
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        if(mediaPlayer.isPlaying()){
            mediaPlayer.stop();
        }
        stopSelf();
    }

    @Override
    public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {

        if(i == MediaPlayer.MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK)
            Toast.makeText(this, "MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK", Toast.LENGTH_LONG).show();
        else if(i == MediaPlayer.MEDIA_ERROR_UNKNOWN)
            Toast.makeText(this, "MEDIA_ERROR_UNKNOWN", Toast.LENGTH_LONG).show();
        return false;
    }

    @Override
    public void onSeekComplete(MediaPlayer mediaPlayer) {

    }

    @Override
    public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {

    }

    @Override
    public boolean onInfo(MediaPlayer mediaPlayer, int i, int i1) {
        return false;
    }

    //music control options
    public void pauseMusic() {
        if(mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }
    }

    public void playMusic() {
        if(!mediaPlayer.isPlaying()){
            mediaPlayer.start();
        }
    }

    public void stopMusic() {
        if(mediaPlayer.isPlaying()) {
            mediaPlayer.seekTo(0);
            mediaPlayer.pause();
        }
        mediaPlayer.seekTo(0);
    }

    public Boolean getPlayState() {
        return mediaPlayer.isPlaying();
    }

    public int currentTime() {
        return mediaPlayer.getCurrentPosition();
    }

    public int maxTime() {
        return mediaPlayer.getDuration();
    }
}
