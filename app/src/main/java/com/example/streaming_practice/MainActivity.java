package com.example.streaming_practice;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener {

    String TAG = "[Main Activity]";

    private ProgressBar progressBar;
    private MediaPlayer mediaPlayer;
    private int mediaFileLengthInMilliseconds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate();");

        mediaPlayer = new MediaPlayer();

        mediaPlayer.setOnBufferingUpdateListener(this);
        mediaPlayer.setOnCompletionListener(this);

        progressBar = findViewById(R.id.progressBar);

        TextView songInfo = findViewById(R.id.songInfoTextView);

        Button playBtn = findViewById(R.id.playButton);
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Play Button onClick();");

                playMedia();

                if (playBtn.getText().toString().equals("재생")) {
                    playBtn.setText("일시 정지");
                }
                if (playBtn.getText().toString().equals("재생 중")) {
                    playBtn.setText("일시 정지");
                } else {
                    playBtn.setText("재생 중");
                }
            }
        });
    }

    private void playMedia() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(("http://13.124.197.8/123.mp3"))
                            .build();
                    Response response = client.newCall(request).execute();
                    mediaPlayer.setDataSource(String.valueOf(response.body().byteStream()));
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer.prepareAsync();
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mediaFileLengthInMilliseconds = mediaPlayer.getDuration();
                            mediaPlayer.start();
//                            updateProgressBar();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void updatedProgressBar() {
        handler.postDelayed(mUpdateTimeTask, 100);
    }

    private Runnable mUpdateTimeTask = new Runnable() {
        @Override
        public void run() {
            handler.sendEmptyMessage(0);
            updatedProgressBar();
        }
    };

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        progressBar.setSecondaryProgress(percent);
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        // 다음 곡 재생 또는 플레이어 종료
    }

    private final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // 재생 위치 받아오기
            int currentPosition = mediaPlayer.getCurrentPosition();
            int progress = (int) (((float) currentPosition / mediaFileLengthInMilliseconds) * 100);
            progressBar.setProgress(progress);
        }
    };



    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart()");
    }

    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume()");
    }

    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause()");
    }

    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop()");
    }

    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy()");
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        } else {

        }
        mediaPlayer.release();
    }

}