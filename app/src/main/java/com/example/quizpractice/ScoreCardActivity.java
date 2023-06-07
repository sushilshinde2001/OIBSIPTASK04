package com.example.quizpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ScoreCardActivity extends AppCompatActivity {
    TextView correct,wrong,total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_card);
        Intent fromActivity=getIntent();
        correct=findViewById(R.id.correct);
        wrong=findViewById(R.id.wrong);
        total=findViewById(R.id.total);
        int rightans=fromActivity.getIntExtra("Write_answers",0);
        int wronganswer=fromActivity.getIntExtra("wrong_answer",0);


        correct.setText("Correct: "+ rightans );
        wrong.setText("Wrong: "+ wronganswer);
        total.setText("Completed: "+ (rightans+wronganswer));

    }
}