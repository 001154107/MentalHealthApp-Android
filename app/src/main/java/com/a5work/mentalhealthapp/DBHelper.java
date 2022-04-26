package com.a5work.mentalhealthapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.a5work.mentalhealthapp.Models.User;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBName = "Login.db";

    public DBHelper(@Nullable Context context) {
        super(context, DBName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create table users(username TEXT primary key, password TEXT, email TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");

    }

    //insert Data
    public Boolean insertData(User user){
        SQLiteDatabase MyDB = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", user.getUsename());
        contentValues.put("password", user.getPassword());
        contentValues.put("email", user.getEmail());
        long result = MyDB.insert("users", null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    //check username
    public boolean checkUsername(String username){
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[]{username});
        if(cursor.getCount() >0)
            return true;
        return false;
    }

    //check username and password
    public boolean checkUserNamePassword(String username, String password){
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?",
                new String[]{username, password});
        if(cursor.getCount()>0)
            return true;
        return false;
    }
}
