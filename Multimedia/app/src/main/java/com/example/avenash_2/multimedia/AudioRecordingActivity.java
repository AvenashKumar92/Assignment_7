package com.example.avenash_2.multimedia;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.Date;

public class AudioRecordingActivity extends AppCompatActivity {

    // 1. Create an object for MeadiaRecorder
    MediaRecorder recorder;
    // To display the status of AudioRecording test
    TextView tvStatus;
    Button btnStart;
    Button btnStop;
    Button btnStartPlay;
    Button btnStopPlay;

    // To play the recorded the audio from SDCard
    MediaPlayer mp;

    String currentFilePath;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_recording);

        tvStatus=findViewById(R.id.tvStatus);
        btnStart=findViewById(R.id.btnStart);
        btnStop=findViewById(R.id.btnStop);
        btnStartPlay=findViewById(R.id.btnStartPlay);
        btnStopPlay=findViewById(R.id.btnStopPlay);


        recorder = new MediaRecorder();
        //2. Set the audio source MIC for recording
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        //3. Set the output format with the extension of amr with the high quality NB (WB-low quality) for audio
        recorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB);
        //4. Set the Encoder
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        //5. Set recording file path/name
        createNewAudioFile();

    }

    private void createNewAudioFile(){

        android.text.format.DateFormat df = new android.text.format.DateFormat();
        String currentTime= (String) df.format("yyyy-MM-dd hh-mm-ss", new Date());
        currentFilePath=getResources().getString(R.string.audio_storage_path) +
                getResources().getString(R.string.app_name)+
                " - "+
                currentTime+
                getResources().getString(R.string.audio_file_ext);
        currentFilePath="/sdcard/test.amr";
        recorder.setOutputFile(currentFilePath);
    }

    /**
     * This method is responsible for start voice recording
     * @param v
     */
    public void onStart(View v){
        btnStart.setEnabled(false);
        btnStop.setEnabled(true);
        btnStartPlay.setEnabled(false);
        btnStopPlay.setEnabled(false);
        try {
            tvStatus.setText("Start Recording");
            recorder.setOutputFile(currentFilePath);
            recorder.prepare(); // Check all the conditions specified with media recorder
            recorder.start();

       } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //This method is responsible for stop on going voice recording
    public void onStop(View v){
        tvStatus.setText("Stop Recording");
        recorder.stop();
        btnStop.setEnabled(false);
        btnStart.setEnabled(true);
        btnStartPlay.setEnabled(true);
        btnStopPlay.setEnabled(false);
    }

    //This method is responsible for playing recorded audio file
    public void onStartPlay(View v) {
        tvStatus.setText("Playing Audio");
        mp = new MediaPlayer();
        try {
            mp.setDataSource(currentFilePath);
            mp.prepare();
            mp.start();
            btnStart.setEnabled(false);
            btnStop.setEnabled(false);
            btnStartPlay.setEnabled(false);
            btnStopPlay.setEnabled(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //This method is responsible for stop playing recorded audio file
    public void onStopPlay(View v){
        tvStatus.setText("Audio Stopped");
        mp.stop();
        mp.release();
        btnStart.setEnabled(true);
        btnStop.setEnabled(false);
        btnStartPlay.setEnabled(true);
        btnStopPlay.setEnabled(false);
    }
}
