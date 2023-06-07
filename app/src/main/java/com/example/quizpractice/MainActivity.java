package com.example.quizpractice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView questionText;
    Button next;
    LinearLayout container;
    TextView scoreView;
    CardView cardView;
    private ArrayList<QuestionModel> QuestionAnwerSet;
    private int score=0;
    public int wrongQuestion=0;
    public int position=0;
    SeekBar seekBar;


    private int count=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        questionText=findViewById(R.id.question);
        cardView=findViewById(R.id.cardView);
//        option1=findViewById(R.id.option1);
//        option2=findViewById(R.id.option2);
//        option3=findViewById(R.id.option3);
//        option4=findViewById(R.id.option4);
        container=findViewById(R.id.container);
        scoreView=findViewById(R.id.score);
        next=findViewById(R.id.next);
        seekBar=findViewById(R.id.seekBar);
        seekBar.setMax(10);
        QuestionAnwerSet=new ArrayList<>();
        QuestionAnwerSet.add(new QuestionModel("What kind of animal is Big Mama in the film &qout; The Fox and the Hound&qout;? ","Cat","Fox","Owl","Woodpecker","Owl"));
        QuestionAnwerSet.add(new QuestionModel("Flick is the leading character in which film?","Monsters Inc","A Bug's Life","Cars","Wall-E","A Bug's Life"));
        QuestionAnwerSet.add(new QuestionModel("Kingdom of the Sun was the original title of which Disney film?","The Hunchback of Notre dame","The Emperor's New Groove","Mulan","Pocahontas","The Emperor's New Groove"));
        QuestionAnwerSet.add(new QuestionModel("What was known as Sondheim's Kabuki musical?","Pacific Overtures","Farces","Pacific Rim","Floor Steps","Pacific Overtures"));
        QuestionAnwerSet.add(new QuestionModel("In which year did New York have its very first Premier of musical version of Peter Pan?","1903","1904","1905","1906","1904"));
        QuestionAnwerSet.add(new QuestionModel("What was the first show at the Ziegfeld Theater in February 1927?","Boat","The Rain","A Little Bird","Rio Rita","Rio Rita"));
        QuestionAnwerSet.add(new QuestionModel("Which show main character are vernon Gersch and Sonia Walk?","They're Playing our song","Sing in the rain","The Dark Moon","I am Paradise","They're Playing our song"));
        QuestionAnwerSet.add(new QuestionModel("In which year did New York have its very first Premier of musical version of Peter Pan?","1903","1904","1905","1906","1904"));
        QuestionAnwerSet.add(new QuestionModel("What was the first show at the Ziegfeld Theater in February 1927?","Boat","The Rain","A Little Bird","Rio Rita","Rio Rita"));
        QuestionAnwerSet.add(new QuestionModel("Which show main character are vernon Gersch and Sonia Walk?","They're Playing our song","Sing in the rain","The Dark Moon","I am Paradise","They're Playing our song"));




        for (int i=1;i<5;i++){
            container.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkAnswer(((Button)view));

                }
            });
        }
        animation(questionText,0,QuestionAnwerSet.get(position).getQuestionText());
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scoreView.setText(score +":" + wrongQuestion);
//                cardView.setCardBackgroundColor(Color.argb(100,84,177,177));
                next.setAlpha(0.5f);
                next.setEnabled(false);
                position++;
                enableOption(true);
                if(position==QuestionAnwerSet.size()){
                    enableOption(false);
                    Intent intent = new Intent(MainActivity.this, ScoreCardActivity.class);
                    intent.putExtra("Write_answers",score);
                    intent.putExtra("wrong_answer",wrongQuestion);
                    startActivity(intent);

                    //send user to score Activity
                    return;
                }
                count=1;
                animation(questionText,0,QuestionAnwerSet.get(position).getQuestionText());

            }
        });



    }
    private void animation(View view,int value,final String data){
        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500).setStartDelay(100).setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(@NonNull Animator animator) {


                if(value==0 && count<5){
                    String option="";
                    if(count==1){
                        option=QuestionAnwerSet.get(position).getOption1();
                        
                    } else if (count==2) {
                        option=QuestionAnwerSet.get(position).getOption2();
                        
                    } else if (count==3) {
                        option=QuestionAnwerSet.get(position).getOption3();
                        
                    } else if (count==4) {
                        option=QuestionAnwerSet.get(position).getOption4();
                        
                    }

                    animation(container.getChildAt(count),0,option);
                    count++;
//                    playAnim(option1,0);
//                    count++;
//                    playAnim(option2,0);
//                    count++;
//                    playAnim(option3,0);
//                    count++;
//                    playAnim(option4,0);
//                    count++;

                }
            }

            @Override
            public void onAnimationEnd(@NonNull Animator animator) {
                if(value==0){
                    try {
                        ((TextView)view).setText(data);
                    }catch (ClassCastException e){
                        ((Button)view).setText(data);
                    }
                    view.setTag(data);
                    animation(view,1,data);
                }

            }

            @Override
            public void onAnimationCancel(@NonNull Animator animator) {

            }

            @Override
            public void onAnimationRepeat(@NonNull Animator animator) {

            }
        });
    }
    @SuppressLint("ResourceType")
    private void checkAnswer(Button optionSelected){
        enableOption(false);
        next.setEnabled(true);
        next.setAlpha(1);
        if (optionSelected.getText().toString().equals(QuestionAnwerSet.get(position).getCorrectOption())) {
            score++;
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                cardView.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF0ADC75")));
//            }
//            Animation ani = AnimationUtils.loadAnimation(this,R.anim.scale);
//            cardView.startAnimation(ani);



            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                optionSelected.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF0ADC75")));
            }

            // Animation for correct selection (if block)
//            ScaleAnimation scaleAnimation = new ScaleAnimation(1f, 1.2f, 1f, 1.2f,
//                    Animation.RELATIVE_TO_SELF, 0.5f,
//                    Animation.RELATIVE_TO_SELF, 0.5f);
//            scaleAnimation.setDuration(300);
//            cardView.startAnimation(scaleAnimation);
        } else {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                cardView.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFB52323")));
//            }





//            Animation scale = AnimationUtils.loadAnimation(this,R.anim.scale);
//            cardView.startAnimation(scale);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                optionSelected.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFB52323")));
            }
            Button correctans=container.findViewWithTag(QuestionAnwerSet.get(position).getCorrectOption());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                correctans.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF0ADC75")));
            }
            wrongQuestion++;

             //Animation for wrong selection (else block)
//            ScaleAnimation scaleAnimation = new ScaleAnimation(1f, 1.2f, 1f, 1.2f,
//                    Animation.RELATIVE_TO_SELF, 0.5f,
//                    Animation.RELATIVE_TO_SELF, 0.5f);
//            scaleAnimation.setDuration(300);
//            cardView.startAnimation(scaleAnimation);
        }
        seekBar.setProgress(score+wrongQuestion);

//            // Animation for correct selection (if block)
//            if(optionSelected.getText().toString().equals(list.get(position).getCorrectOption())){
//                //container.setVisibility(View.INVISIBLE);
//                cardView.setCardBackgroundColor(Color.GREEN);
//                score++;
//            }else{
//
//                cardView.setCardBackgroundColor(Color.RED);
//                wrongQuestion++;
//            }

    }
    private void enableOption(boolean enable){
        for(int i=1;i<5;i++){
            container.getChildAt(i).setEnabled(enable);
            if(enable){
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                    cardView.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#54B1B1")));
//
//                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    container.getChildAt(i).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));
                }

            }
        }
    }
}

