package com.example.pandacare.PandaCalm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;


import com.example.pandacare.R;

public class PandaCalm extends AppCompatActivity {

    CountDownTimer countDownTimer;
    TextView timerTextView;
    SeekBar timerSeekBar;
    Button playMusicButton;
    Boolean counter = false;

    public void playButtonClicked(View view) {

        // counter to track whether the music is playing or not
        if (counter){
            pandaCalmReset();
        }else {

            counter = true;
            playMusicButton.setText("Stop Music");
            timerSeekBar.setEnabled(false);

            // start playing music service
            startService(new Intent(this, musicService.class));

            // multiply 1000 in order to work with millisecond, add 100 is to readjust the timer
            countDownTimer = new CountDownTimer(timerSeekBar.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long l) {
                    timerUpdate((int) l / 1000);

                }

                @Override
                public void onFinish(){
                    //stop music when finish
                    pandaCalmReset();

                }
            }.start();
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panda_calm);

        timerTextView = findViewById(R.id.timerTextView);
        timerSeekBar = findViewById(R.id.timerSeekBar);
        playMusicButton = findViewById(R.id.playMusicButton);

        //Max timer is 600 sec, which is 10 minutes
        timerSeekBar.setMax(600);
        //starting position of seek bar
        timerSeekBar.setProgress(0);

        //seekBar activity which record data when user sliding the seek bar
        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                timerUpdate(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    // update the timer according to the seek bar progress
    public void timerUpdate(int i){

        // changing from sec to minutes
        int min = i/60;
        int sec = i - (min * 60);

        // making sure the timer display is 2-digit
        String minString = Integer.toString(min);
        if (min <= 9){
            minString = "0" + minString;
        }

        String secString = Integer.toString(sec);
        if (sec <= 9){
            secString = "0" + secString;
        }

        timerTextView.setText(minString+":"+secString);
    }

    // reset the panda calm function and stop music
    public void pandaCalmReset(){
        counter = false;
        stopService(new Intent(this, musicService.class));
        timerSeekBar.setProgress(0);
        timerSeekBar.setEnabled(true);
        timerTextView.setText("00:00");
        playMusicButton.setText("Play Music");
        countDownTimer.cancel();
    }
}