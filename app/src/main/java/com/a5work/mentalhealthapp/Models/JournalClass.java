package com.a5work.mentalhealthapp.Models;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;

public class JournalClass implements Serializable {
    private int id; // used in storage
    private int mentalRating;
    private int physicalRating;
    private Calendar date;
    private String reason;
    private boolean saved; // is set to true when saved into file
    private String text;

    public JournalClass(int mentalRating, int physicalRating, Calendar date, String reason, boolean saved) {
        this.id = this.hashCode();
        this.mentalRating = mentalRating;
        this.physicalRating = physicalRating;
        this.date = date;
        this.reason = reason;
        this.saved = saved;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMentalRating() {
        return mentalRating;
    }

    public void setMentalRating(int mentalRating) {
        this.mentalRating = mentalRating;
    }

    public int getPhysicalRating() {
        return physicalRating;
    }

    public void setPhysicalRating(int physicalRating) {
        this.physicalRating = physicalRating;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
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

    public String nameFile() { // Creates a unique file name for each instance.
        return "Journal_" + this.getId() + this.getDate() + ".ser";
    }



    // Constant with a file name
    public String fileName = this.nameFile();

    // Serializes an object and saves it to a file
    public boolean SaveToFile(Context context){ //
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
        return true;
    }



}

