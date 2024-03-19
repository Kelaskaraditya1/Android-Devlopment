package com.starkindustries.room_library.Util;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.starkindustries.room_library.Data.Contact_Dao;
import com.starkindustries.room_library.Model.Contact;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
@Database(entities = {Contact.class},version=1,exportSchema = false)
public abstract class ContactRoomDatabase extends RoomDatabase {
    public  abstract Contact_Dao contactdao();
    public static final int NO_OF_THREADS=4;
    private static volatile ContactRoomDatabase INSTANCE;
    public static final ExecutorService dbwriteExecutor = Executors.newFixedThreadPool(NO_OF_THREADS);
    public static ContactRoomDatabase getdatabase(final Context context)
    {
        if(INSTANCE==null)
        {
            synchronized (ContactRoomDatabase.class)
            {
                if(INSTANCE==null)
                    INSTANCE= Room.databaseBuilder(context.getApplicationContext(),ContactRoomDatabase.class,"Contacts_Database").build();
            }
        }
        return INSTANCE;
    }
    RoomDatabase.Callback roomdatabasecallback = new RoomDatabase.Callback()
    {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            dbwriteExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    Contact_Dao contactDao = INSTANCE.contactdao();
                    contactDao.delete_all();
                    Contact Aditya = new Contact("Aditya","8591059220");
                    contactDao.create_contact(Aditya);
                    Contact Sandeep = new Contact("Sandeep","9819375722");
                    contactDao.create_contact(Sandeep);

                }
            });
        }
    };
}
