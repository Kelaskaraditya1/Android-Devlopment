package com.starkindustries.room_library.Model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.starkindustries.room_library.Data.ContactRepo;

import java.util.ArrayList;

public class ContactModel extends AndroidViewModel {
    public static ContactRepo repo;
    public static LiveData<ArrayList<Contact>> contact_list;
    public ContactModel(@NonNull Application application) {
        super(application);
        repo=new ContactRepo(application);
        contact_list=repo.get_Contacts();
    }
    public LiveData<ArrayList<Contact>> get_contacts()
    {
        return contact_list;
    }
    public void insert_contact(Contact contact)
    {
        repo.insert(contact);
    }

}
