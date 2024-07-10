package com.example.gestionnairemdp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.gestionnairemdp.table.Account;
import com.example.gestionnairemdp.table.Admin;

import java.util.ArrayList;

public class DatabaseManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "GestionnaireMDP.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*
            CREATION DES DEUX TABLES DANS LA BASE DE DONNÉES
         */
        String creationAdminTableSql = "create Table Admin (" +
                "id integer primary key," +
                "username text not null," +
                "password text not null)";
        db.execSQL(creationAdminTableSql);

        String creationAccountTableSql = "create Table Account (" +
                "id integer primary key," +
                "name text not null," +
                "username text not null," +
                "password text not null," +
                "description text)";
        db.execSQL(creationAccountTableSql);

        /*
            INSERTION DU SEUL ADMIN AYANT LES DROITS
         */
        String insertAdminInDB = "INSERT INTO Admin (username, password) " +
                "VALUES ('', '')";
        db.execSQL(insertAdminInDB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public Admin adminInDB() {
        Admin admin = new Admin();
        String sql = "Select * from Admin";
        @SuppressLint("Recycle") Cursor cursor = this.getWritableDatabase().rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
            @SuppressLint("Range") String username = cursor.getString(cursor.getColumnIndex("username"));
            @SuppressLint("Range") String password = cursor.getString(cursor.getColumnIndex("password"));


            admin.setId(id);
            admin.setUsername(username);
            admin.setPassword(password);
        }
        return admin;
    }

    public ArrayList<Account> getAllAccountsInDB() {
        ArrayList<Account> accounts = new ArrayList<>();
        String sql = "Select * from Account";
        @SuppressLint("Recycle") Cursor cursor = this.getWritableDatabase().rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
                @SuppressLint("Range") String username = cursor.getString(cursor.getColumnIndex("username"));
                @SuppressLint("Range") String password = cursor.getString(cursor.getColumnIndex("password"));
                @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex("description"));

                Account account = new Account();
                account.setId(id);
                account.setName(name);
                account.setUsername(username);
                account.setPassword(password);
                account.setDescription(description);

                accounts.add(account);
                cursor.moveToNext();
            }
        }
        return accounts;
    }

    public void insertAccountToDB(String name, String username, String password, String description) {
        String sql = "INSERT INTO Account (name, username, password, description) " +
                "VALUES ('" + name + "', '" + username + "', '" + password + "', '" + description + "')";
        this.getWritableDatabase().execSQL(sql);
    }

    public Account getAccountByName(String accountName) {
        Account account = new Account();
        String sql = "Select * from Account Where name = '" + accountName + "'";
        @SuppressLint("Recycle") Cursor cursor = this.getWritableDatabase().rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
            @SuppressLint("Range") String username = cursor.getString(cursor.getColumnIndex("username"));
            @SuppressLint("Range") String password = cursor.getString(cursor.getColumnIndex("password"));
            @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex("description"));

            account.setId(id);
            account.setName(name);
            account.setUsername(username);
            account.setPassword(password);
            account.setDescription(description);
        }
        return account;
    }

    public void updateAccount(int id, String name, String username, String password, String description) {
        String sql = "UPDATE Account SET name = '" + name + "', username = '" + username
                + "', password = '" + password + "', description = '" + description + "' WHERE id = " + id;
        this.getWritableDatabase().execSQL(sql);
    }

    public void deleteAccount(int id) {
        String sql = "DELETE FROM Account WHERE id = " + id;
        this.getWritableDatabase().execSQL(sql);
    }
}
