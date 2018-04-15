package com.example.avenash_2.multimedia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.avenash_2.multimedia.camera_gallery.CameraGallery;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onCamGallery(View view) {
        Intent intent=new Intent(this, CameraGallery.class);
        startActivity(intent);
    }

    public void onVideoRec(View view) {
        Intent intent=new Intent(this, VideoRecordingActivity.class);
        startActivity(intent);
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
