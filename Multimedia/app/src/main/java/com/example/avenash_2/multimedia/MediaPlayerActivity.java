package com.example.avenash_2.multimedia;

import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

public class MediaPlayerActivity extends AppCompatActivity {
    MediaPlayer mPlayer;
    SeekBar sBar;
    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player);

        mPlayer = MediaPlayer.create(getApplicationContext(),R.raw.test);
        sBar = (SeekBar) findViewById(R.id.sBar);
        sBar.setMax(mPlayer.getDuration());

        sBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mPlayer.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        updateSeekBar();
    }

    Runnable run = new Runnable() {
        @Override
        public void run() {
            updateSeekBar();
        }
    };

    // Method to handle update SeekBar
    private void updateSeekBar() {
        sBar.setProgress(mPlayer.getCurrentPosition());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                updateSeekBar();
            }
        },1000);

    }

    public void test(View v) {
        switch (v.getId()) {
            case R.id.backward:
                mPlayer.seekTo(mPlayer.getCurrentPosition() - mPlayer.getDuration() / 10);
                break;
            case R.id.play:
                mPlayer.start();
                break;
            case R.id.pause:
                mPlayer.pause();
                break;
            case R.id.stop:
                mPlayer.stop();
                mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.test);
                break;
            case R.id.forward:
                mPlayer.seekTo(mPlayer.getCurrentPosition() + mPlayer.getDuration() / 10);
                break;
        }
    }
}
