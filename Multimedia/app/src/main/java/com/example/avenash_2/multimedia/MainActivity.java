package com.example.avenash_2.multimedia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onCamGallery(View view) {
    }

    public void onVideoRec(View view) {
    }

    public void onAudioRec(View view) {
        Intent intent=new Intent(this, AudioRecordingActivity.class);
        startActivity(intent);
    }

    public void onVideoView(View view) {
    }

    public void onMediaPlayer(View view) {
    }
}
