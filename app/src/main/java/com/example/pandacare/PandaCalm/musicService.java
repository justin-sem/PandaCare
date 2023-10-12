package com.example.pandacare.PandaCalm;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.example.pandacare.R;

public class musicService extends Service {

    private MediaPlayer mediaPlayer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    // start playing music service
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.beat_of_nature);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();        //play music
        return START_STICKY;
    }

    // stop playing music service
    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
    }
}
