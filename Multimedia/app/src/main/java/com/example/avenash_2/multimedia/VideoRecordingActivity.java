package com.example.avenash_2.multimedia;

import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.io.IOException;

public class VideoRecordingActivity extends AppCompatActivity {

    // Declare an Object for SurfaceView
    SurfaceView sView;
    // Declare an Object for SurfaceHolder to manage the SurfaceView
    SurfaceHolder sHolder;
    // Declare an object for MediaRecorder
    MediaRecorder recorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_recording);

        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
        CamcorderProfile profile =
                CamcorderProfile.get(CamcorderProfile.QUALITY_HIGH);
        recorder.setProfile(profile);
        String vFilePath = Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/videotest.mp4";
        recorder.setOutputFile(vFilePath);
        try {
            recorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Configure the Id from XML UI SurfaceView Component
        sView = (SurfaceView) findViewById(R.id.svVideoRec);
        // get the Holder for the SurfaceView object
        sHolder = sView.getHolder();
        // Add a Callback interface for this holder.
        sHolder.addCallback(new SurfaceHolder.Callback() {
            /* A client may implement this interface to receive information about changes to
            the surface.*/
            // This is called immediately after the surface is first created.
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                // Once the surface is created, displaying the video preview through holder
                recorder.setPreviewDisplay(holder.getSurface());
            }

            // This is called immediately after any structural changes (orientation or size) //have been made to the surface.
            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int
                    height) {
            }

            // This is called immediately before a surface is being destroyed.
            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
            }
        });
    }

    public void onStart(View view) {
        recorder.start();
    }

    public void onStop(View view) {
        recorder.stop();
    }
}
