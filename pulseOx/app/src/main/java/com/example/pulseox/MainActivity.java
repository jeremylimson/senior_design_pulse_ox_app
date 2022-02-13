package com.example.pulseox;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    private int saturation = 0;
    private ProgressBar progressBar;
    private Button startProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progress_bar);
        startProgress = findViewById(R.id.startProgress);

        CountDownTimer countDownTimer = new CountDownTimer(11*1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                saturation += 10;
                progressBar.setProgress(saturation);
                progressBar.setMax(100);
            }

            @Override
            public void onFinish() {

            }
        };
        startProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDownTimer.start();
            }
        });
    }
}