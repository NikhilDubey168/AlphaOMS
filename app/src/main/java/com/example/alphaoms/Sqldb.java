package com.example.alphaoms;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Sqldb extends SQLiteOpenHelper {
    public Sqldb(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query1 = "CREATE TABLE users (Username TEXT, Email TEXT, Password TEXT)";
        sqLiteDatabase.execSQL(query1); 
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void register( String Username, String Password, String Email ){
        ContentValues cv = new ContentValues();
        cv.put("Username",Username);
        cv.put("Email",Email);
        cv.put("Password",Password);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("users",null,cv);
        db.close();
    }
    public int login(String username,String password){
        int temp = 0;
        String str[] = new String[2];
        str[0]=username;
        str[1]=password;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select* from users where username=? and password=?",str);
        if(c.moveToFirst()){
            temp =1;
        }
        return temp;
    }
}
