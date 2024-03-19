package com.starkindustries.recyclerview_with_database.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.starkindustries.recyclerview_with_database.Keys.Keys;
import com.starkindustries.recyclerview_with_database.Model.Contacts;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {
    public DatabaseHandler(Context context) {
        super(context, Keys.DATABASE_NAME, null, Keys.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create Table " + Keys.TABLE_NAME + " ( " + Keys.ID + " Integer Primary Key Autoincrement," + Keys.NAME + " Text," + Keys.NUMBER + " Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("Drop table if exists " + Keys.TABLE_NAME);
        onCreate(db);
    }

    public void add_contact(Contacts contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(Keys.NAME, contact.getName());
        content.put(Keys.NUMBER, contact.getNumber());
        db.insert(Keys.TABLE_NAME, null, content);
        Log.d("Contact_added", "Contact " + contact.name + " added Sucessfully");
    }

    public Contacts select_contact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Keys.TABLE_NAME, new String[]{Keys.ID, Keys.NAME, Keys.NUMBER}, Keys.ID + "=?", new String[]{String.valueOf(id)}, null, null, null);
        Contacts contact = new Contacts();
        if (cursor != null) {
            cursor.moveToFirst();
            contact.setId(cursor.getInt(0));
            contact.setName(cursor.getString(1));
            contact.setNumber(cursor.getString(2));
        }
        return contact;
    }

    public ArrayList<Contacts> get_contacts() {
        ArrayList<Contacts> contact_list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + Keys.TABLE_NAME, null);
        if (cursor != null) {
            cursor.moveToFirst();
            while (cursor.moveToNext())
                contact_list.add(new Contacts(cursor.getInt(0), cursor.getString(1), cursor.getString(2)));
        }
        return contact_list;
    }

    public void delete_contact(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Keys.TABLE_NAME,Keys.ID+"=?",new String[]{String.valueOf(id)});
    }
    public void update_contact(Contacts contact)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(Keys.NAME,contact.getName());
        content.put(Keys.NUMBER,contact.getNumber());
        db.update(Keys.TABLE_NAME,content,Keys.ID+"=?",new String[]{String.valueOf(contact.getId())});
    }
    public int getcount()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from "+ Keys.TABLE_NAME,null);
        if(cursor!=null) {
            cursor.moveToFirst();
        }
        return cursor.getCount();
    }
    public int getid(String name)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select "+Keys.ID+" from "+Keys.TABLE_NAME+" where "+Keys.NAME+" = "+name,null);
        if(cursor!=null) {
            return cursor.getInt(1);
        }
        else
            return -1;
    }
}
