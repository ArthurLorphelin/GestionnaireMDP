package com.example.gestionnairemdp.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
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
import com.example.gestionnairemdp.table.Account;

public class AccountDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.account_details);

        TextView accountNameMenuTextView = findViewById(R.id.account_details_name_in_menu);
        accountNameMenuTextView.setText(AllAccounts.ACCOUNT_NAME.toUpperCase());

        ImageButton previousPageImageButton = findViewById(R.id.previous_page_account_details);
        previousPageImageButton.setOnClickListener(v -> {
            Intent intentBackToAllAccounts = new Intent(getApplicationContext(), AllAccounts.class);
            startActivity(intentBackToAllAccounts);
        });

        ImageButton profilePageImageButton = findViewById(R.id.profile_image_button_account_details);
        profilePageImageButton.setOnClickListener(v -> {
            Intent intentProfilePage = new Intent(getApplicationContext(), ProfilePage.class);
            startActivity(intentProfilePage);
        });

        DatabaseManager databaseManager = new DatabaseManager(getApplicationContext());
        Account currentAccount = databaseManager.getAccountByName(AllAccounts.ACCOUNT_NAME);
        EditText accountNameEdittext = findViewById(R.id.account_details_name_in_modify);
        accountNameEdittext.setText(currentAccount.getName());
        EditText accountUsernameEdittext = findViewById(R.id.account_details_username_in_modify);
        accountUsernameEdittext.setText(currentAccount.getUsername());
        EditText accountDescriptionEditText = findViewById(R.id.account_details_description_in_modify);
        accountDescriptionEditText.setText(currentAccount.getDescription());
        EditText accountPasswordEditText = findViewById(R.id.account_details_password_in_modify);
        accountPasswordEditText.setText(currentAccount.getPassword());

        ImageButton passwordVisibleImageButton = findViewById(R.id.account_details_password_visible_image_button);
        ImageButton passwordInvisbleImageButton = findViewById(R.id.account_details_password_invisible_image_button);
        passwordVisibleImageButton.setOnClickListener(v -> {
            passwordVisibleImageButton.setVisibility(View.GONE);
            passwordInvisbleImageButton.setVisibility(View.VISIBLE);
            accountPasswordEditText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        });
        passwordInvisbleImageButton.setOnClickListener(v -> {
            passwordInvisbleImageButton.setVisibility(View.GONE);
            passwordVisibleImageButton.setVisibility(View.VISIBLE);
            accountPasswordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        });

        ImageButton copyPasswordImageButton = findViewById(R.id.copy_password_image_button);
        copyPasswordImageButton.setOnClickListener(v -> {
            ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clipData = ClipData.newPlainText("password", accountPasswordEditText.getText().toString().trim());
            clipboardManager.setPrimaryClip(clipData);
        });

        Button modifyAccountButton = findViewById(R.id.modify_account_to_database_button);
        modifyAccountButton.setOnClickListener(v -> {
            String name = accountNameEdittext.getText().toString();
            String username = accountUsernameEdittext.getText().toString();
            String password = accountPasswordEditText.getText().toString();
            String description = accountDescriptionEditText.getText().toString();

            if (!name.isEmpty() && !username.isEmpty() && !password.isEmpty()) {
                databaseManager.updateAccount(currentAccount.getId(), name, username, password, description);
                Intent intentBackToAllAccounts = new Intent(getApplicationContext(), AllAccounts.class);
                startActivity(intentBackToAllAccounts);
            } else {
                TextView fillTheEditTextTextView = findViewById(R.id.text_view_fill_the_edit_text_modify_account);
                fillTheEditTextTextView.setVisibility(View.VISIBLE);
                if (name.isEmpty()) {
                    accountNameEdittext.setHintTextColor(getResources().getColor(R.color.red));
                } if (username.isEmpty()) {
                    accountUsernameEdittext.setHintTextColor(getResources().getColor(R.color.red));
                } if (password.isEmpty()) {
                    accountPasswordEditText.setHintTextColor(getResources().getColor(R.color.red));
                }
            }
        });

        Button deleteAccountButton = findViewById(R.id.delete_account_to_database_button);
        deleteAccountButton.setOnClickListener(v -> {
            Intent intentDeleteAccount = new Intent(getApplicationContext(), DeleteAccount.class);
            startActivity(intentDeleteAccount);
        });
    }
}