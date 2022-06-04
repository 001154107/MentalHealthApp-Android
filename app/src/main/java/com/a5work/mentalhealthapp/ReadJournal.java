package com.a5work.mentalhealthapp;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.a5work.mentalhealthapp.Models.JournalClass;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.util.Date;

public class ReadJournal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) { // TODO - Make Read from file
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_journal);

        View prevEntry = findViewById(R.id.month_navigation_previous);
        View nextEntry = findViewById(R.id.month_navigation_next);
        View todaysDate = findViewById(R.id.todaysDateTV);
        View entryDate = findViewById(R.id.EntryDate);
        View reason = findViewById(R.id.JournalText);
        GraphView graph = findViewById(R.id.graph);

        LineGraphSeries<DataPoint> emotionalRating = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
        LineGraphSeries<DataPoint> physicalRating= new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 6),
                new DataPoint(2, 4),
                new DataPoint(3, 2),
                new DataPoint(4, 4)
        });
        graph.addSeries(physicalRating);
        graph.addSeries(emotionalRating);


        FloatingActionButton read_WriteJournalfltbtn = findViewById(R.id.floatingActionButton);
        read_WriteJournalfltbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent WriteJournalIntent = new Intent(ReadJournal.this,MainActivity.class);
                startActivity(WriteJournalIntent);
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private JournalClass rtnObjFromFile(LocalDateTime requestedDate){ //TODO - Make read from file

        LocalDateTime closestDate = LocalDateTime.now();
        LocalDateTime fileDate;
        String filename = "Jounral_"+closestDate+"gson";
        Boolean fileFound;

        fileDate =
        FileInputStream fOutStream = getApplicationContext().openFileInput();

        while (!fileFound){
            try {
                FileInputStream fOutStream = getApplicationContext().openFileInput();

            } catch(Exception e) {
                fileFound = false;
            }
        }



        return null;
    }
}