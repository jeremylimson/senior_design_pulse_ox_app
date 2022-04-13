package com.example.roomwordsample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ProgressBar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int saturation = 0;
    private int hr = 0;

    private ProgressBar oxygenBar;
    private Button startOx;

    private ProgressBar heartBar;
    private Button startHr;

    private WordViewModel mWordViewModel;
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final WordListAdapter adapter = new WordListAdapter(new WordListAdapter.WordDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        mWordViewModel = new ViewModelProvider(this).get(WordViewModel.class);
        mWordViewModel.delete();
        mWordViewModel.getAllWords().observe(this, words -> {
            // Update the cached copy of the words in the adapter.
            adapter.submitList(words);
        });

        FloatingActionButton word = findViewById(R.id.floatingActionButton);
        word.setOnClickListener( view -> {
            Intent intent = new Intent(MainActivity.this, NewWordActivity.class);
            startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
        });

        FloatingActionButton setting = findViewById(R.id.floatingActionButton2);
        setting.setOnClickListener( view -> {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
        });

        FloatingActionButton trends = findViewById(R.id.floatingActionButton3);
        trends.setOnClickListener( view -> {
            Intent intent = new Intent(MainActivity.this, TrendsActivity.class);
            startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
        });

        oxygenBar = findViewById(R.id.progress_bar_oxygen);
        startOx = findViewById(R.id.startOxygen);

        heartBar = findViewById(R.id.progress_bar_heart_rate);
        startHr = findViewById(R.id.startHeart);

        CountDownTimer countDownTimer = new CountDownTimer(11*1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // for testing, use random data to simulate normal oxygen saturation levels
                int random_oxygen = new Random().nextInt(7) + 89;
                saturation = random_oxygen;

                int random_heart_rate = new Random().nextInt(7) + 63;
                hr = random_heart_rate;

                // TODO: cast word in database to int and display to GUI
                oxygenBar.setProgress(saturation);
                oxygenBar.setMax(100);

                heartBar.setProgress(hr);
                heartBar.setMax(200);
            }

            @Override
            public void onFinish() {

            }
        };

        startOx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDownTimer.start();
            }
        });

        startHr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { countDownTimer.start(); }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Word word = new Word(data.getStringExtra(NewWordActivity.EXTRA_REPLY));
            mWordViewModel.insert(word);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }
}