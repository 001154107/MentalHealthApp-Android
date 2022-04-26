package com.a5work.mentalhealthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button main_ReadJournalbtn = findViewById(R.id.ReadJournalbtn);
        main_ReadJournalbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent readJournalIntent = new Intent(MainActivity.this,ReadJournal.class);
                startActivity(readJournalIntent);
            }
        });

        }



    }
