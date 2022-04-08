package com.example.sqlite_math_game;

import java.util.List;
import java.util.concurrent.TimeUnit;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class QuestionActivity extends AppCompatActivity {
    List<Question> quesList;
    int score = 0;
    int qid = 0;

    Question currentQue;
    TextView txtQue, txtTime, txtScore;
    Button btn1, btn2, btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        QuizHelper db = new QuizHelper(this);

        // to get all the quetonall questions
        quesList = db.getAllQuestions();
        // this will store the current question
        currentQue = quesList.get(qid);

        txtQue = (TextView) findViewById(R.id.txtQuestion);
        btn1 = (Button) findViewById(R.id.button1);
        btn2 = (Button) findViewById(R.id.button2);
        btn3 = (Button) findViewById(R.id.button3);

        txtScore = (TextView) findViewById(R.id.score);
        txtTime = (TextView) findViewById(R.id.timers);

        // tihs method will set the question
        setQuestionView();
        txtTime.setText("00:02:00");

        // A timer of 60 seconds to play for, with an interval of 1 second (1000 milliseconds)
        CounterClass timer = new CounterClass(60000, 1000);
        timer.start();

        btn1.setOnClickListener(v -> {

            // passing the button text to other method
            // to check whether the answer is correct or not
            getAnswer(btn1.getText().toString());
        });

        btn2.setOnClickListener(v -> getAnswer(btn2.getText().toString()));
        btn3.setOnClickListener(v -> getAnswer(btn3.getText().toString()));
    }

    public void getAnswer(String AnswerString) {
        if (currentQue.getANSWER().equals(AnswerString)) {

        // if conditions matches the score will be incremented by 1
        // and set the text of the score view
            score++;
            txtScore.setText("Score : " + score);
        } else {
            //finish the game
            Intent intent = new Intent(QuestionActivity.this,
                    ResultActivity.class);

            Bundle b = new Bundle();
            b.putInt("score", score);
            // Put your score to your next
            intent.putExtras(b);
            startActivity(intent);
            finish();
        }
        if (qid < 20) {

        // if questions are not over then do this
            currentQue = quesList.get(qid);
            setQuestionView();
        } else {

            // if over do this
            Intent intent = new Intent(QuestionActivity.this, ResultActivity.class);
            Bundle b = new Bundle();
            b.putInt("score", score); // Your score
            intent.putExtras(b); // Put your score to your next
            startActivity(intent);
            finish();
        }

    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @SuppressLint("NewApi")
    public class CounterClass extends CountDownTimer {

        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
// TODO Auto-generated constructor stub
        }

        @Override
        public void onFinish() {
            txtTime.setText("Time is up");
        }

        @Override
        public void onTick(long millisUntilFinished) {
// TODO Auto-generated method stub

            long millis = millisUntilFinished;
            @SuppressLint("DefaultLocale") String hms = String.format(
                    "%02d:%02d:%02d",
                    TimeUnit.MILLISECONDS.toHours(millis),
                    TimeUnit.MILLISECONDS.toMinutes(millis)
                    -TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS
                            .toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis)
                    -TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS
                            .toMinutes(millis)));
            System.out.println(hms);
            txtTime.setText(hms);
        }

    }

    private void setQuestionView() {

// the method which will put all things together
        txtQue.setText(currentQue.getQUESTION());
        btn1.setText(currentQue.getOPTA());
        btn2.setText(currentQue.getOPTB());
        btn3.setText(currentQue.getOPTC());

        qid++;
    }

}
