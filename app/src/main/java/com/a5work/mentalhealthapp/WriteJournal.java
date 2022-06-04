package com.a5work.mentalhealthapp;


import static java.lang.String.*;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.a5work.mentalhealthapp.Models.JournalClass;

import java.time.LocalDateTime;
import java.util.Calendar;


public class WriteJournal extends AppCompatActivity {

    EditText date, reason;
    RatingBar MentalRatingBar, PhysicalRatingBar;
    Button submitButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writejournal);

        MentalRatingBar = findViewById(R.id.ratingBar);
        PhysicalRatingBar = findViewById(R.id.ratingBar1);
        date = findViewById(R.id.DatePicker1);
        //date.setInputType(InputType.TYPE_NULL);
        reason = findViewById(R.id.Reason);
        //reason.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);

        Calendar todayDate = Calendar.getInstance();
        int day = todayDate.get(Calendar.DAY_OF_MONTH);
        int month = todayDate.get(Calendar.MONTH);
        int year = todayDate.get(Calendar.YEAR);

        date.setText(format("%d/%d/%d", day, month + 1, year));

        submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            //Performing action on Button Click
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) { // Make class and store file
                try {
                    int MentalRate = MentalRatingBar.getNumStars();
                    int PhysicRate = PhysicalRatingBar.getNumStars();
                    String reasonTxt = String.valueOf(reason.getText());

                    JournalClass entry = new JournalClass(MentalRate, PhysicRate, LocalDateTime.now(), reasonTxt);

                    if (entry.SaveToFile(getBaseContext())) {
                        Toast.makeText(getBaseContext(), "Entry Saved!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), ReadJournal.class);
                        startActivity(intent);

                    }
                } catch (Exception ex) {
                    Toast.makeText(getBaseContext(), ex.toString(), Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
