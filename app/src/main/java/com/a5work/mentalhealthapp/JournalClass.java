package com.a5work.mentalhealthapp;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.File;
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
    
    // Functions
    public boolean SaveToFile(){ // #TODO



     return saved;
    }
}

