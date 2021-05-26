package com.example.project_mcs_lab;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText Username;
    EditText Password;
    AccountDB accountDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Username = findViewById(R.id.Username);
        Password = findViewById(R.id.Password);

        accountDB = new AccountDB(this);
    }

    public void signup(View view) {
        Intent intentregister = new Intent(this, RegisterPage.class);
        startActivity(intentregister);
    }


    public void Login(View view) {
        String username = Username.getText().toString();
        String password = Password.getText().toString();
        int terisi = 0, cek = 0;
        if(username == null || username.equals("")){
            Toast.makeText(this,"Username Must Be Filled!", Toast.LENGTH_LONG).show();
        }
        else{
            terisi++;
        }
        if(password == null || password.equals("")){
            Toast.makeText(this,"Password Must Be Filled!", Toast.LENGTH_LONG).show();
        }
        else{
            terisi++;
        }
        if(terisi == 2) {
            int check = 0;
            int count = accountDB.countTableSize();

            if(count == 0){
                Toast.makeText(this, "You must register first!", Toast.LENGTH_SHORT).show();
            }
            else if(count != 0){
                for (int i = 1; i <= count; i++) {
                    Account account = accountDB.getAccount(i);
                    if (account.getUsername().equals(username) && account.getPassword().equals(password)) {
                        Toast.makeText(this, "Login Successfully!", Toast.LENGTH_SHORT).show();
                        int tampung_user_id = i;
                        Intent homeintent = new Intent(this, HomePage.class);
                        homeintent.putExtra("USER_ID", tampung_user_id);
                        startActivity(homeintent);
                        finish();
                    }
                    else{
                        check++;
                    }
                }
            }
            if(check == count){
                Toast.makeText(this, "You must register first!", Toast.LENGTH_SHORT).show();
            }
        }
    }

}