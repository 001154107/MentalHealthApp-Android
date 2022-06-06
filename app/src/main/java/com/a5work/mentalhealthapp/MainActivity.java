package com.a5work.mentalhealthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.a5work.mentalhealthapp.Models.User;

public class MainActivity extends AppCompatActivity {

    EditText username, password, repassword, email;
    Button signin, signup;
    DBHelper DB;
    User user;

    @Override
    protected void onRestart() {
        super.onRestart();
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }

    /*

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }

     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       username = (EditText) findViewById(R.id.username);
       password = (EditText) findViewById(R.id.password);
       repassword = (EditText) findViewById(R.id.confirmpw);
       email = (EditText) findViewById(R.id.email);

       signin = (Button) findViewById(R.id.signin);
       signup = (Button) findViewById(R.id.signup);

       DB = new DBHelper(this);

       //signUp btn
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameTxt = username.getText().toString();
                String passwordTxt = password.getText().toString();
                String repassTxt = repassword.getText().toString();
                String emailTxt = email.getText().toString();

                user =  new User(usernameTxt, passwordTxt, emailTxt);

                if(usernameTxt.equals("") || passwordTxt.equals("") || repassTxt.equals("") || emailTxt.equals("") ){
                    Toast.makeText(MainActivity.this, "Please enter all the fields", Toast.LENGTH_LONG).show();
                }else{
                    //check password == repassword
                    if(passwordTxt.equals(repassTxt)){
                        Boolean checkuser = DB.checkUsername(usernameTxt);
                        //check username existed or not
                        if(checkuser == false){
                            Boolean insertData = DB.insertData(user);
                            //insert data into DB
                            if(insertData == true){
                                Toast.makeText(MainActivity.this, "Registered successfully", Toast.LENGTH_LONG).show();
                                //move to jounal page
                                Intent intent = new Intent(getApplicationContext(), WriteJournal.class);
                                startActivity(intent);
                            }
                        }else{
                            Toast.makeText(MainActivity.this, "User already exists", Toast.LENGTH_LONG).show();
                        }

                    }else{
                        Toast.makeText(MainActivity.this, "Password is not matching", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });



        //signin btn
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}