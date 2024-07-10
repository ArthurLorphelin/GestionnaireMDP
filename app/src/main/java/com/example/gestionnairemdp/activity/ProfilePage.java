package com.example.gestionnairemdp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gestionnairemdp.R;

public class ProfilePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.profile_page);

        ImageButton previousPageImageButton = findViewById(R.id.previous_page_profile_page);
        previousPageImageButton.setOnClickListener(v -> {
            Intent intentPreviousPage = new Intent(getApplicationContext(), AllAccounts.class);
            startActivity(intentPreviousPage);
        });

        Button logOutButton = findViewById(R.id.log_out_button);
        logOutButton.setOnClickListener(v -> {
            Intent intentLogOut = new Intent(getApplicationContext(), SignIn.class);
            startActivity(intentLogOut);
        });
    }
}