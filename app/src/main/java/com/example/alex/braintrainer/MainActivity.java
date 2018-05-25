package com.example.alex.braintrainer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    Button startBtn;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    Timer timer = new Timer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView qTextView = findViewById(R.id.questionTextView);
        //Lazy solution
        Button btn0 = (Button) findViewById(R.id.solutionBtn0);
        Button btn1 = (Button) findViewById(R.id.solutionBtn1);
        Button btn2 = (Button) findViewById(R.id.solutionBtn2);
        Button btn3 = (Button) findViewById(R.id.solutionBtn3);


        //startBtn = findViewById(R.id.startBtn);
        Random rand  = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        qTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));
        locationOfCorrectAnswer = rand.nextInt(4);

        int incorrectAnswer;
        for(int i = 0 ; i< 4 ; i++){
            if( i == locationOfCorrectAnswer){
                answers.add(a+b);
            } else {
                incorrectAnswer = rand.nextInt(41);
                while(incorrectAnswer == a+b){
                    incorrectAnswer = rand.nextInt(41);
                }
                answers.add(incorrectAnswer);
            }
        }
        btn0.setText(Integer.toString(answers.get(0)));
        btn1.setText(Integer.toString(answers.get(1)));
        btn2.setText(Integer.toString(answers.get(2)));
        btn3.setText(Integer.toString(answers.get(3)));

    }

    public void chooseAnswer(View view){
        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
            //Correct Answer
        }
    }

    public void start(View view){
    }

    public void playAgain(View view){

    }

    public void startTimer(){

    }
}
