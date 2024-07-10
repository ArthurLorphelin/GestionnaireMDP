package com.example.gestionnairemdp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gestionnairemdp.R;

public class IncorrectLogs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.incorrect_logs);

        Button backToSignInButton = findViewById(R.id.back_to_sign_in_page_button);
        backToSignInButton.setOnClickListener(v -> {
            Intent intentBackToSignIn = new Intent(getApplicationContext(), SignIn.class);
            startActivity(intentBackToSignIn);
        });

    }
}