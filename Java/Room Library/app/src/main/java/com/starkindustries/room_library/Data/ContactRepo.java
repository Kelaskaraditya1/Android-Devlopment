package com.starkindustries.room_library.Data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.starkindustries.room_library.Model.Contact;
import com.starkindustries.room_library.Util.ContactRoomDatabase;

import java.util.ArrayList;

public class ContactRepo {
    public static Contact_Dao contactdao;
    public static LiveData<ArrayList<Contact>> contact_list;
    public ContactRepo(Application app)
    {
        ContactRoomDatabase db = ContactRoomDatabase.getdatabase(app);
        contactdao=db.contactdao();
        contact_list=contactdao.get_all();
    }
    public LiveData<ArrayList<Contact>> get_Contacts()
    {
        return contact_list;
    }
    public void insert(Contact contact)
    {
        ContactRoomDatabase.dbwriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                contactdao.create_contact(contact);
            }
        });
    }


}
