package com.starkindustries.database.Database;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.starkindustries.database.Keys.Keys;
import com.starkindustries.database.Model.Contact;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper
{
    public DatabaseHandler(Context context)
    {
        super(context, Keys.DATABASE_NAME,null,1);
    }
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("Create Table "+Keys.CONTACTS_TABLE+"( "+Keys.ID+" Integer Primary Key Autoincrement, "+Keys.NAME+" Text,"+Keys.PHONE_NO+" Text)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
         db.execSQL("Drop Table if exists "+Keys.CONTACTS_TABLE);
         onCreate(db);
    }
    public void add_contact(Contact contact)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put("Name",contact.getName());
        content.put("Phone_No",contact.getPhone_no());
        db.insert(Keys.CONTACTS_TABLE,null,content);
        Log.d("Contact_Added",contact.getName()+" "+contact.getPhone_no());
//        db.close();
    }
    public Contact getContact(int id)
    {
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor cursor = db.query(Keys.CONTACTS_TABLE,new String[]{Keys.ID,Keys.NAME,Keys.PHONE_NO},Keys.ID+"=?",new String[]{String.valueOf(id)},null,null,null);
        Contact contact = new Contact();
        if(cursor!=null)
            cursor.moveToFirst();
        contact.setId(Integer.parseInt(cursor.getString(0)));
        contact.setName(cursor.getString(1));
        contact.setPhone_no(cursor.getString(2));
        return contact;
    }
    public ArrayList<Contact> display_contacts()
    {
        ArrayList<Contact> contact_list = new ArrayList<Contact>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from "+Keys.CONTACTS_TABLE,null);
        if(cursor!=null)
        {cursor.moveToFirst();
            while(cursor.moveToNext())
            {
                Contact contact = new Contact();
                contact.setName(cursor.getString(1));
                contact.setPhone_no(cursor.getString(2));
                contact_list.add(contact);
            }
        }
        return contact_list;
    }
    public int contact_update(Contact contact)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(Keys.NAME,contact.getName());
        content.put(Keys.PHONE_NO,contact.getPhone_no());
        return db.update(Keys.CONTACTS_TABLE,content,Keys.ID+"=?",new String[]{String.valueOf(contact.getId())});
    }
    public void delete_contact(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Keys.CONTACTS_TABLE,Keys.ID+"=?",new String[]{String.valueOf(id)});
        Log.d("Contact",id+" Contact deleted");
    }
    public int get_count()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from "+Keys.CONTACTS_TABLE,null);
        if(cursor!=null)
            cursor.moveToFirst();
        return cursor.getCount();
    }

}
