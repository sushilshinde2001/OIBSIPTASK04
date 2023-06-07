package com.example.quizpractice;

import static android.graphics.Color.*;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MathematicQuizz extends AppCompatActivity {
        TextView timer1,question,status;
    int writeans=0,noOfQuestion=0;
        Button option1,option2,option3,option4;
    Random random=new Random();
    int answerIndex;
    ArrayList<Integer> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mathematic_quizz);

        timer1=findViewById(R.id.timer1);
        question=findViewById(R.id.question);
        option1=findViewById(R.id.option1);
        option2=findViewById(R.id.option2);
        option3=findViewById(R.id.option3);
        option4=findViewById(R.id.option4);
        status=findViewById(R.id.score);
        newQuestion();
        countDownTimer.start();

    }
     CountDownTimer countDownTimer =new  CountDownTimer(30000,1000) {
        @Override
        public void onTick(long l) {
            int t= (int) (l/1000);
            String newt=String.valueOf(t);
            if(t<=9){
                newt="0"+newt;
            }
            timer1.setText(newt+"s");
        }

        @Override
        public void onFinish() {
            timer1.setText("00");
            option1.setEnabled(false);
            option2.setEnabled(false);
            option3.setEnabled(false);
            option4.setEnabled(false);
        }
    };

    public void newQuestion(){
        ///answerIndex++;
        int a=random.nextInt(21);
        int b=random.nextInt(21);
        answerIndex=random.nextInt(4);
        question.setText(a+"+"+b);
        list.clear();
        for(int i=0;i<4;i++){

            if(i==answerIndex){
                list.add(a+b);

            }else{
                int newNumber=random.nextInt(41);
                while(newNumber==(a+b)){
                    newNumber=random.nextInt(41);
                }
                list.add(newNumber);
            }

        }
        option1.setText(String.valueOf(list.get(0)));
        option2.setText(String.valueOf(list.get(1)));
        option3.setText(String.valueOf(list.get(2)));
        option4.setText(String.valueOf(list.get(3)));
    }

    public void chooseAnswer(View view) {
        if (String.valueOf(answerIndex).equals(view.getTag().toString())) {
            writeans++;

            //status.setText("Correct!");

        } else {
            //status.setText("Wrong...!");
        }
        noOfQuestion++;
        status.setText(writeans + "/" + noOfQuestion);
        newQuestion();
    }
}