package com.a5work.mentalhealthapp;


import static java.lang.String.*;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Button;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;


public class WriteJournal extends AppCompatActivity {

    DatePickerDialog picker;
    EditText  date, reason;
    RatingBar ratingBar, ratingBar1;
    Button submitButton;
    TextView tvw;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_writejournal);

            ratingBar=findViewById(R.id.ratingBar);
            ratingBar1=findViewById(R.id.ratingBar1);
            date = findViewById(R.id.DatePicker1);
            date.setInputType(InputType.TYPE_NULL);
            reason = findViewById(R.id.Reason);
            reason.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
            date.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick (View v) {
                    final Calendar cldr = Calendar.getInstance();
                    int day = cldr.get (Calendar.DAY_OF_MONTH);
                    int month = cldr.get(Calendar.MONTH);
                    int year = cldr.get(Calendar.YEAR);
                    //date picker dialog
                    picker = new DatePickerDialog(WriteJournal.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            date.setText(format("%d/%d/%d", dayOfMonth, monthOfYear + 1, year));
                        }
                    }, year, month, day);
                    picker.show();
                }
            });

            submitButton=findViewById(R.id.submitButton);
            submitButton.setOnClickListener(new View.OnClickListener() {
                //Performing action on Button Click
                @Override
                public void onClick(View v) {
                    tvw.setText(format("Selected Date: %s", date.getText()));
                    String rating = valueOf(ratingBar.getRating());
                    String rating1 = valueOf(ratingBar1.getRating());

                }
            });
        }
}
