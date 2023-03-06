package com.example.streaming_practice;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AsyncPlayer;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener {

    String TAG = "[Main Activity]";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate();");

        // 1. 안드로이드 어플에서 재생 버튼을 클릭한다.
        // 2. 재생 버튼 클릭 시 SSH 클라이언트를 사용하여 nginx 서버에 접속한다.
        // 3. mediaplayer 클래스를 사용하여 mp3 파일을 스트리밍한다.
        // 4. 스트리밍 시 해당 곡의 정보를 서버로부터 받아와서 songInfoTextView에 넣어준다.

        Button playBtn = findViewById(R.id.playButton);
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Play Button onClick();");

            }
        });
    }



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
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {

    }

    @Override
    public void onCompletion(MediaPlayer mp) {

    }
}