package com.a5work.mentalhealthapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class ReadJournal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) { // TODO - Make Read from file
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_journal);
        GraphView graph = findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
        graph.addSeries(series);


        FloatingActionButton read_WriteJournalfltbtn = findViewById(R.id.floatingActionButton);
        read_WriteJournalfltbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent WriteJournalIntent = new Intent(ReadJournal.this,MainActivity.class);
                startActivity(WriteJournalIntent);
            }
        });

    }
}