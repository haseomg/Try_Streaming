package com.example.streaming_practice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 서버의 디렉토리에 있는 mp3 파일을 안드로이드 스튜디오에서 재생시켜야 해
        // 원래 생각한 방식은 mysql 데이터 베이스에 파일을 업로드 시켜서 가져오는 것이였는데,
        // 이 부분에서 너무 막혔어. 다시 정리해서 시간 정해두고 둘 다 구현 시도해보자.
        // 일요일까지 방향 안 잡히면 슬랙에 질문하자 피니야
    }
}