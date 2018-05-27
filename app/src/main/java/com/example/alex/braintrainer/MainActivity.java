package com.example.alex.braintrainer;

import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout gameLayout;

    ArrayList<Integer> answers = new ArrayList<Integer>();
    Button btn0;
    Button btn1;
    Button btn2;
    Button btn3;
    Button startBtn;
    Button playAgainBtn;

    TextView qTextView;
    TextView scoreTextView;
    TextView timeTextView;
    TextView resultTextView;
    Timer timer = new Timer();
    int score = 0;
    int locationOfCorrectAnswer;
    int numberOfQuestions = 0;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gameLayout = (ConstraintLayout) findViewById(R.id.gameConstraintLayout);
        startBtn = (Button) findViewById(R.id.startBtn);
        qTextView = findViewById(R.id.questionTextView);
        scoreTextView = (TextView) findViewById(R.id.scoreTextView);
        timeTextView = (TextView) findViewById(R.id.timeTextView);
        resultTextView = (TextView) findViewById(R.id.resultsTextView);
        //Lazy solution
        btn0 = (Button) findViewById(R.id.solutionBtn0);
        btn1 = (Button) findViewById(R.id.solutionBtn1);
        btn2 = (Button) findViewById(R.id.solutionBtn2);
        btn3 = (Button) findViewById(R.id.solutionBtn3);
        playAgainBtn = (Button) findViewById(R.id.playAgainBtn);
        //startBtn = findViewById(R.id.startBtn);

    }

    public void chooseAnswer(View view) {
        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {
            //Correct Answer
            score++;
        } else {
            //Wrong Answer
        }
        numberOfQuestions++;
        scoreTextView.setText(Integer.toString(score) + " / " + Integer.toString(numberOfQuestions));
        generateQuestion();
    }

    public void generateQuestion() {
        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        qTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));
        locationOfCorrectAnswer = rand.nextInt(4);

        // clears previous button such that button update properly
        answers.clear();

        int incorrectAnswer;
        for (int i = 0; i < 4; i++) {
            if (i == locationOfCorrectAnswer) {
                answers.add(a + b);
            } else {
                incorrectAnswer = rand.nextInt(41);
                while (incorrectAnswer == a + b) {
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

    public void start(View view) {
        startBtn.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.playAgainBtn));

    }

    public void playAgain(View view) {
        score = 0;
        numberOfQuestions = 0;

        timeTextView.setText("30s");
        scoreTextView.setText("0 / 0");
        resultTextView.setText("");
        playAgainBtn.setVisibility(View.INVISIBLE);
        generateQuestion();

        new CountDownTimer(3010, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timeTextView.setText(String.valueOf(millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish() {
                timeTextView.setText("0s");
                resultTextView.setText("Your final score is " + Integer.toString(score)+ " / " + Integer.toString(numberOfQuestions));
                playAgainBtn.setVisibility(View.VISIBLE);
            }
        }.start();
    }

}
