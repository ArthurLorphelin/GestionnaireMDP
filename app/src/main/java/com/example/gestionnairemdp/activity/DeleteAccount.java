package com.example.gestionnairemdp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gestionnairemdp.DatabaseManager;
import com.example.gestionnairemdp.R;
import com.example.gestionnairemdp.table.Account;

public class DeleteAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.delete_account);

        ImageButton profileImageButton = findViewById(R.id.profile_image_button_delete_account);
        profileImageButton.setOnClickListener(v -> {
            Intent intentProfilePage = new Intent(getApplicationContext(), ProfilePage.class);
            startActivity(intentProfilePage);
        });

        TextView accountNameTextView = findViewById(R.id.account_name_in_delete_account_text_view);
        accountNameTextView.setText(AllAccounts.ACCOUNT_NAME);

        Button yesButton = findViewById(R.id.yes_delete_account_button);
        yesButton.setOnClickListener(v -> {
            DatabaseManager databaseManager = new DatabaseManager(getApplicationContext());
            Account currentAccount = databaseManager.getAccountByName(AllAccounts.ACCOUNT_NAME);
            databaseManager.deleteAccount(currentAccount.getId());
            Intent intentBackToAllAccounts = new Intent(getApplicationContext(), AllAccounts.class);
            startActivity(intentBackToAllAccounts);
        });

        Button noButton = findViewById(R.id.no_delete_account_button);
        noButton.setOnClickListener(v -> {
            Intent intentBackToAllAccounts = new Intent(getApplicationContext(), AllAccounts.class);
            startActivity(intentBackToAllAccounts);
        });
    }
}