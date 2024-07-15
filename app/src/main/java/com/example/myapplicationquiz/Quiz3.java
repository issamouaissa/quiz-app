package com.example.myapplicationquiz;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplicationquiz.Quiz2;

import java.sql.Time;

public class Quiz3 extends AppCompatActivity {
    Button next;
    int score;
    RadioButton rb;
    RadioGroup rg;
    TextView textViewTimer;

    String correctresponses="Eugène Delacroix";

    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz3);
        next = findViewById(R.id.bNext);
        rg = findViewById(R.id.rg);
        rb = findViewById(R.id.rb);
        textViewTimer = findViewById(R.id.textViewTimer);
        score = getIntent().getIntExtra("score", 0);
        startTimer();
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rg.getCheckedRadioButtonId()==-1){
                    Toast.makeText(getApplicationContext(),"veuiller choisir une reponse",Toast.LENGTH_SHORT).show();

                }else {
                    rb= findViewById(rg.getCheckedRadioButtonId());
                    if (rb.getText().toString().equals(correctresponses)) score+=1;
                    Intent il = new Intent(getApplicationContext(), Quiz4.class);
                    il.putExtra("score",score);
                    startActivity(il);
                    finish();
                }
            }
        });


    }
    private void startTimer() {
        countDownTimer = new CountDownTimer(20000, 1000) {
            public void onTick(long millisUntilFinished) {

                long secondsRemaining = millisUntilFinished / 1000;
                textViewTimer.setText(secondsRemaining + "s");
            }
            public void onFinish() {

                Intent intent = new Intent(getApplicationContext(), Quiz4.class);
                intent.putExtra("score", score);
                startActivity(intent);
                finish();
            }
        }.start();
    }
}