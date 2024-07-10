package com.example.gestionnairemdp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gestionnairemdp.DatabaseManager;
import com.example.gestionnairemdp.R;
import com.example.gestionnairemdp.table.Admin;

public class SignIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.sign_in);

        DatabaseManager databaseManager = new DatabaseManager(getApplicationContext());

        Button signInButton = findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(v -> {
            EditText usernameEditText = findViewById(R.id.sign_in_username);
            EditText passwordEditText = findViewById(R.id.sign_in_password);
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            Admin admin = databaseManager.adminInDB();
            if (username.equals(admin.getUsername())) {
                if (password.equals(admin.getPassword())) {
                    Intent intentSignIn = new Intent(getApplicationContext(), AllAccounts.class);
                    startActivity(intentSignIn);
                } else {
                    Intent intentIncorrectLogs = new Intent(getApplicationContext(), IncorrectLogs.class);
                    startActivity(intentIncorrectLogs);
                }
            } else {
                Intent intentIncorrectLogs = new Intent(getApplicationContext(), IncorrectLogs.class);
                startActivity(intentIncorrectLogs);
            }
        });
    }
}