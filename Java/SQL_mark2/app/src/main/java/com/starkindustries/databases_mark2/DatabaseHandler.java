package com.starkindustries.databases_mark2;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;
import java.sql.*;

import androidx.annotation.Nullable;

import java.security.Key;
import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper
{
    public Context context;
    public DatabaseHandler(@Nullable Context context) {
        super(context,Keys.TABLE_NAME, null, Keys.VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create Table "+Keys.TABLE_NAME+" ( "+Keys.SID+" Text Primary key, "+Keys.NAME+" Text, "+Keys.PHONE_NO+" Text, "+Keys.USERNAME+" Text unique , "+Keys.PASSWORD+" Text Unique)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists "+Keys.TABLE_NAME);
        onCreate(db);
    }
    public void create_user(User user)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Keys.SID,user.getSid());
        values.put(Keys.NAME,user.getName());
        values.put(Keys.PHONE_NO,user.getPhone_no());
        values.put(Keys.USERNAME,user.getUsername());
        values.put(Keys.PASSWORD,user.getPassword());
        db.insert(Keys.TABLE_NAME,null,values);
        Toast.makeText(context, "User "+user.getName()+" inserted successfully", Toast.LENGTH_SHORT).show();
//        db.close();
    }
    public void update_user(User user,String sid)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Keys.NAME,user.getName());
        values.put(Keys.PHONE_NO,user.getPhone_no());
        values.put(Keys.USERNAME,user.getUsername());
        values.put(Keys.PASSWORD,user.getPassword());
        db.update(Keys.TABLE_NAME,values,Keys.SID+"=?",new String[]{sid});
        Toast.makeText(context, "User "+ user.getName()+" updated successfully", Toast.LENGTH_SHORT).show();
        db.close();
    }
    public void delete_user(String sid)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        db.delete(Keys.TABLE_NAME,Keys.SID+"=?",new String[]{sid});
    }
    public int get_user_count()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from "+Keys.TABLE_NAME,null);
        if(cursor!=null)
            cursor.moveToFirst();
        return cursor.getCount();
    }
    public boolean user_exists(String username,String password)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Keys.TABLE_NAME,null,Keys.USERNAME+"=?",new String[]{username},null,null,null);
        if(cursor!=null) {
            cursor.moveToFirst();
            return true;
        }
        return false;
    }
    public ArrayList<User> get_users_list() {
        int count = 0;
        ArrayList<User> user_list = new ArrayList<User>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + Keys.TABLE_NAME, null);
        if (cursor != null) {
            cursor.moveToFirst();
            do {
                User user = new User();
                user.setSid(cursor.getString(0));
                user.setName(cursor.getString(1));
                user.setPhone_no(cursor.getString(2));
                user.setUsername(cursor.getString(3));
                user_list.add(user);
            }
            while(cursor.moveToNext());
        }
        cursor.close();
        return user_list;
    }
    public User get_user(String sid)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Keys.TABLE_NAME,new String[]{Keys.SID,Keys.NAME,Keys.PHONE_NO,Keys.USERNAME,Keys.PASSWORD},Keys.SID+"=?",new String[]{sid},null,null,null);
        if(cursor!=null)
            cursor.moveToFirst();
        User user = new User();
        user.setName(cursor.getString(1));
        user.setUsername(cursor.getString(3));
        user.setPhone_no(cursor.getString(2));
        cursor.close();
        return user;
    }
    public String get_sid(String name)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Keys.TABLE_NAME,new String[]{Keys.SID,Keys.NAME,Keys.PHONE_NO,Keys.USERNAME,Keys.PASSWORD},Keys.NAME+"=?",new String[]{name},null,null,null);
        if(cursor!=null)
            cursor.moveToFirst();
        return cursor.getString(0);
    }
    public boolean login_check(String username,String password)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Keys.TABLE_NAME,new String[]{Keys.SID,Keys.NAME,Keys.PHONE_NO,Keys.USERNAME,Keys.PASSWORD},Keys.PASSWORD+"=?",
                new String[]{password},null,null,null);
        if(cursor.getCount()==0) {
            return false;
        }
        else {
            cursor.moveToFirst();
            Log.d("user_identified"," sid: "+cursor.getString(0)+" Name: "+cursor.getString(1));
            return true;
        }
    }
    public boolean login_using_username_password(String username,String password)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Keys.TABLE_NAME,new String[]{Keys.SID,Keys.NAME,Keys.PHONE_NO,Keys.USERNAME,Keys.PASSWORD},Keys.USERNAME+"=?"+" and "+Keys.PASSWORD+"=?",new String[]{username,password},null,null,null);
        if(cursor.getCount()==0)
            return false;
        else {
            cursor.moveToFirst();
            Log.d("user_identified_successfully","Sid: "+cursor.getString(0)+" Name: "+cursor.getString(1)+" Phone no: "+cursor.getString(2));
            return true;
        }
    }
    public static void jdbcConnection()
    {

    }


}
