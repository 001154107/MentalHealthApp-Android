package com.a5work.mentalhealthapp.Models;

import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

public class JournalClass implements Serializable {
    private int id; // used in storage
    private float mentalRating;
    private float physicalRating;
    private LocalDateTime date;
    private String reason;
    private boolean saved; // is set to true when saved into file
    private String text;
    public String fileName;


    @RequiresApi(api = Build.VERSION_CODES.O)
    public JournalClass(float mentalRating, float physicalRating, LocalDateTime date, String reason) {
        this.id = this.hashCode();
        this.mentalRating = mentalRating;
        this.physicalRating = physicalRating;
        this.date = date;
        this.reason = reason;
        this.fileName = nameFile();
        // this.saved = saved;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getMentalRating() {
        return mentalRating;
    }

    public void setMentalRating(float mentalRating) {
        this.mentalRating = mentalRating;
    }

    public float getPhysicalRating() {
        return physicalRating;
    }

    public void setPhysicalRating(float physicalRating) {
        this.physicalRating = physicalRating;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JournalClass that = (JournalClass) o;
        return getId() == that.getId()
                && getMentalRating() == that.getMentalRating()
                && getPhysicalRating() == that.getPhysicalRating()
                && getDate().equals(that.getDate())
                && Objects.equals(getReason(), that.getReason());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMentalRating(), getPhysicalRating(), getDate(), getReason());
    }

    @Override
    public String toString() {
        return "JournalClass{" +
                "mentalRating=" + mentalRating +
                ", physicalRating=" + physicalRating +
                ", date=" + date +
                ", reason='" + reason + '\'' +
                '}';
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String nameFile() { // Creates a unique file name for each instance.
      //  String dateStr = String.valueOf(this.getDate());
        String formattedDate = getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy-hh-mm-ss"));
        return "Journal_" +  formattedDate +".gson";
    }


    // Constant with a file name

    // Serializes an object and saves it to a file
    public boolean SaveToFile(@NonNull Context context){ //
        try {
            FileOutputStream fileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(this);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        this.setSaved(true);
        return true;
    }


}

