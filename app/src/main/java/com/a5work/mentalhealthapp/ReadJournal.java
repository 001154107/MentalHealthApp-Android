package com.a5work.mentalhealthapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.a5work.mentalhealthapp.Models.JournalClass;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.io.comparator.LastModifiedFileComparator;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.io.filefilter.WildcardFileFilter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.LinkedList;

public class ReadJournal extends AppCompatActivity {

    private int selEntry = 0;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) { // TODO - Make Read from file
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_journal);

        ImageButton prevEntry = findViewById(R.id.month_navigation_previous);
        ImageButton nextEntry = findViewById(R.id.month_navigation_next);
        GraphView graph = findViewById(R.id.graph);




        LinkedList<JournalClass> entrieslinkedList = new LinkedList<JournalClass>();
        for (File i : getFilesInOrder()) {
            try {
                entrieslinkedList.add(readObjectFromFile(i));
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        DataPoint[] emotionDataPoints = new DataPoint[entrieslinkedList.size()];
        DataPoint[] physicalDataPoints = new DataPoint[entrieslinkedList.size()];

        for(JournalClass i : entrieslinkedList){
            emotionDataPoints[entrieslinkedList.indexOf(i)] = new DataPoint(entrieslinkedList.indexOf(i),i.getMentalRating());
        }

        for(JournalClass i : entrieslinkedList){
            physicalDataPoints[entrieslinkedList.indexOf(i)] = new DataPoint(entrieslinkedList.indexOf(i),i.getPhysicalRating());
        }

        LineGraphSeries<DataPoint> emotionalRating = new LineGraphSeries<>(emotionDataPoints);
        LineGraphSeries<DataPoint> physicalRating = new LineGraphSeries<>(physicalDataPoints);
        graph.addSeries(physicalRating);
        graph.addSeries(emotionalRating);
        emotionalRating.setColor(0xffce93d8);
        physicalRating.setColor(0xff81d4fa);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            readDateAndReason(selEntry, entrieslinkedList);
        }

        prevEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    selEntry++;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        readDateAndReason(selEntry, entrieslinkedList);
                    }
                } catch (Exception e){
                    selEntry = entrieslinkedList.size();
                    Toast.makeText(getBaseContext(),"No more entries",Toast.LENGTH_LONG).show();
                }


            }
        });

        nextEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    selEntry = selEntry - 1;

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        readDateAndReason(selEntry, entrieslinkedList);
                    }
                } catch (Exception e){
                    selEntry = 0;
                    Toast.makeText(getBaseContext(),"No more entries",Toast.LENGTH_LONG).show();
                }

            }
        });




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
    private void readDateAndReason(int i, LinkedList<JournalClass> entrieslinkedList) {
        // REASON and DATE
        String formattedDate = entrieslinkedList.get(i).getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a"));
        TextView entryDate = findViewById(R.id.EntryDate);
        TextView reason = findViewById(R.id.JournalText);
        reason.setText(entrieslinkedList.get(i).getReason());
        entryDate.setText(formattedDate);
    }


    // Get object from a file.
    public JournalClass readObjectFromFile(File file) throws IOException, ClassNotFoundException {
        Object result = null;
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            result = ois.readObject();
            fis.close();
            ois.close();
        }
        return (JournalClass) result;
    }

    // Get the newest file for a specific extension
    public File[] getFilesInOrder() {
        File dir = new File("/data/data/com.a5work.mentalhealthapp/files/");
        FileFilter fileFilter = new WildcardFileFilter("*" + ".gson");
        File[] files = dir.listFiles(fileFilter);

        if (files.length > 0) {
            // The newest file comes first
            Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
        }

        return files;
    }
}