package com.example.gestionnairemdp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gestionnairemdp.DatabaseManager;
import com.example.gestionnairemdp.R;
import com.example.gestionnairemdp.table.Account;

import java.util.ArrayList;
import java.util.Collections;

public class AllAccounts extends AppCompatActivity {
    public static String ACCOUNT_NAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.all_accounts);

        ImageButton profileImageButton = findViewById(R.id.profile_image_button_all_accounts);
        profileImageButton.setOnClickListener(v -> {
            Intent intentProfilePage = new Intent(getApplicationContext(), ProfilePage.class);
            startActivity(intentProfilePage);
        });

        DatabaseManager databaseManager = new DatabaseManager(getApplicationContext());
        ArrayList<Account> allAccountsInDB = databaseManager.getAllAccountsInDB();
        ArrayList<String> accountsName = new ArrayList<>();
        ListView allAccountsListView = findViewById(R.id.list_of_all_accounts_list_view);
        for (int accountIndex = 0; accountIndex < allAccountsInDB.size(); accountIndex++) {
            accountsName.add(allAccountsInDB.get(accountIndex).getName());
        }
        Collections.sort(accountsName);
        ArrayAdapter<String> adapterAccounts = new ArrayAdapter<>(getApplicationContext(), R.layout.list_view_single_item, R.id.list_view_item_text_view, accountsName);
        allAccountsListView.setAdapter(adapterAccounts);

        EditText searchBarAccounts = findViewById(R.id.search_bar_all_accounts);
        searchBarAccounts.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                ArrayList<String> accountsNameDuringSearch = new ArrayList<>();
                for (int accountIndex = 0; accountIndex < accountsName.size(); accountIndex++) {
                    if (accountsName.get(accountIndex).toUpperCase().contains(s.toString().toUpperCase())) {
                        accountsNameDuringSearch.add(accountsName.get(accountIndex));
                    }
                }
                Collections.sort(accountsNameDuringSearch);
                ArrayAdapter<String> adapterAccountsDuringSearch = new ArrayAdapter<>(getApplicationContext(), R.layout.list_view_single_item, R.id.list_view_item_text_view, accountsNameDuringSearch);
                allAccountsListView.setAdapter(adapterAccountsDuringSearch);
            }
        });

        Button addAccountButton = findViewById(R.id.add_account_button);
        addAccountButton.setOnClickListener(v -> {
            Intent intentAddAccount = new Intent(getApplicationContext(), AddAccount.class);
            startActivity(intentAddAccount);
        });

        allAccountsListView.setOnItemClickListener((parent, view, position, id) -> {
            ACCOUNT_NAME = parent.getItemAtPosition(position).toString().trim();
            Intent intentAccountDetails = new Intent(getApplicationContext(), AccountDetails.class);
            startActivity(intentAccountDetails);
        });
    }
}