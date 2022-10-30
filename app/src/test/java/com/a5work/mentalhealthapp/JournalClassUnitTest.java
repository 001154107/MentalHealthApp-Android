package com.a5work.mentalhealthapp;

import org.junit.Test;

import static org.junit.Assert.*;

import com.a5work.mentalhealthapp.Models.JournalClass;

import java.time.LocalDateTime;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class JournalClassUnitTest {
    @Test
    public void CreateJCEntry() {
        JournalClass testEntry = new JournalClass(3,3, LocalDateTime.now(),"This is a Test Entry!");



        assert testEntry.getMentalRating() == 3;
    }
}


