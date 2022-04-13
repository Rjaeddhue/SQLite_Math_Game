package com.example.sqlite_math_game;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        TextView txtRegis = (TextView) findViewById(R.id.txtReg);

        txtRegis.setOnClickListener( v -> {

            Intent intent = new Intent(getApplicationContext(),
                    LoginActivity.class);
            startActivity(intent);
            finish();

        });

    }
}

//    OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
//        @Override
//        public void handleOnBackPressed() {
//            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//            startActivity(intent);
//            finish();
//        }
//    };
//        new LoginActivity().getOnBackPressedDispatcher().addCallback(this, callback);