package com.a5work.mentalhealthapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);

        DBHelper DB = new DBHelper(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String usernameTxt = username.getText().toString();
                String passwordTxt = password.getText().toString();

                if(usernameTxt.equals("") || passwordTxt.equals("")){
                    Toast.makeText(LoginActivity.this, "Please enter all fields", Toast.LENGTH_LONG).show();
                }else{
                    boolean checkuserpass = DB.checkUserNamePassword(usernameTxt, passwordTxt);
                    if(checkuserpass == true){
                        Toast.makeText(LoginActivity.this, "Sign in successfully", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), WriteJournal.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivity.this, "Invalid data", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });


        //get action bar
        getSupportActionBar().setTitle("Login");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
    }
/*
    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = new Intent(getApplicationContext(), WriteJournal.class);
        startActivity(intent);
    }

 */

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}