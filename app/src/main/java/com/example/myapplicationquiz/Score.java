package com.example.myapplicationquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Score extends AppCompatActivity {
    Button btry, blogout;
    TextView tvscore;
    ProgressBar pb;
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        tvscore = findViewById(R.id.tvScore);
        pb = findViewById(R.id.progressBar);
        btry = findViewById(R.id.bTry);
        blogout = findViewById(R.id.bLogout);

        Intent i1 = getIntent();
        score = i1.getIntExtra("score", 0);
        tvscore.setText(score * 100 / 5 + "%");
        pb.setProgress(score * 100 / 5);
        btry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Quiz1.class));
                finish();
            }
        });
        blogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });
    }
}
