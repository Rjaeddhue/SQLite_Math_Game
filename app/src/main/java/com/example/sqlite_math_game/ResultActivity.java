package com.example.sqlite_math_game;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class ResultActivity extends AppCompatActivity {


    GoogleSignInClient mGoogleSignInClient;
    Button signout;
    Button showanswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        TextView textResult = (TextView) findViewById(R.id.textResult);
        showanswer= (Button)findViewById(R.id.answers) ;
        showanswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ResultActivity.this, "clicked", Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(getApplicationContext(),FetchActivity.class);
                startActivity(intent);
            }
        });
        Bundle b = getIntent().getExtras();
        int score = b.getInt("score");
        textResult.setText("Your score is " + " " + score + ". Thanks for playing my game.");



        signout= (Button)findViewById(R.id.btnsignout);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    // ...
                    case R.id.btnsignout:
                        signOut();
                        break;
                }
            };

        });}

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        Toast.makeText(ResultActivity.this, "Signed out successful", Toast.LENGTH_SHORT).show();
                        Intent intent= new Intent(getApplicationContext(),LoginActivity.class);
                        startActivity(intent);
                        // ...
                    }
                });
    }


    public void playagain(View o) {
        Intent intent = new Intent(this, QuestionActivity.class);
        startActivity(intent);
    }






            }


