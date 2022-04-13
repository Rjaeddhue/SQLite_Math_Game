package com.example.sqlite_math_game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FetchActivity extends AppCompatActivity {

    Button button1;
    public static TextView textView1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch);

        button1 = findViewById(R.id.button);
        textView1 = findViewById(R.id.fetcheddata);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchdata process = new fetchdata();
                process.execute();
            }
        });
    }
    }
