package com.example.gestionnairemdp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gestionnairemdp.DatabaseManager;
import com.example.gestionnairemdp.R;

public class AddAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.add_account);

        ImageButton previousPageImageButton = findViewById(R.id.previous_page_add_account);
        previousPageImageButton.setOnClickListener(v -> {
            Intent intentBackToAllAccounts = new Intent(getApplicationContext(), AllAccounts.class);
            startActivity(intentBackToAllAccounts);
        });

        ImageButton profilePageImageButton = findViewById(R.id.profile_image_button_add_account);
        profilePageImageButton.setOnClickListener(v -> {
            Intent intentProfilePage = new Intent(getApplicationContext(), ProfilePage.class);
            startActivity(intentProfilePage);
        });

        EditText passwordEditText = findViewById(R.id.new_account_password_edit_text);
        ImageButton passwordVisibleImageButton = findViewById(R.id.add_account_password_visible_image_button);
        ImageButton passwordInvisbleImageButton = findViewById(R.id.add_account_password_invisible_image_button);
        passwordVisibleImageButton.setOnClickListener(v -> {
            passwordVisibleImageButton.setVisibility(View.GONE);
            passwordInvisbleImageButton.setVisibility(View.VISIBLE);
            passwordEditText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        });
        passwordInvisbleImageButton.setOnClickListener(v -> {
            passwordInvisbleImageButton.setVisibility(View.GONE);
            passwordVisibleImageButton.setVisibility(View.VISIBLE);
            passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        });

        Button addAccountButton = findViewById(R.id.add_account_to_database_button);
        addAccountButton.setOnClickListener(v -> {
            EditText accountNameEditText = findViewById(R.id.new_account_name_edit_text);
            String name = accountNameEditText.getText().toString();
            EditText usernameEditText = findViewById(R.id.new_account_username_edit_text);
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            EditText descriptionEditText = findViewById(R.id.new_account_description_edit_text);
            String description = descriptionEditText.getText().toString();

            DatabaseManager databaseManager = new DatabaseManager(getApplicationContext());
            if (!name.isEmpty() && !username.isEmpty() && !password.isEmpty()) {
                databaseManager.insertAccountToDB(name, username, password, description);
                Intent intentNewAccountAdded = new Intent(getApplicationContext(), AllAccounts.class);
                startActivity(intentNewAccountAdded);
            } else {
                TextView fillTheEditTextTextView = findViewById(R.id.text_view_fill_the_edit_text);
                fillTheEditTextTextView.setVisibility(View.VISIBLE);
                if (name.isEmpty()) {
                    accountNameEditText.setHintTextColor(getResources().getColor(R.color.red));
                } if (username.isEmpty()) {
                    usernameEditText.setHintTextColor(getResources().getColor(R.color.red));
                } if (password.isEmpty()) {
                    passwordEditText.setHintTextColor(getResources().getColor(R.color.red));
                }
            }
        });
    }
}