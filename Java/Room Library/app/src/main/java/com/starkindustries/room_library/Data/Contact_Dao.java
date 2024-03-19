package com.starkindustries.room_library.Data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.starkindustries.room_library.Model.Contact;

import java.util.ArrayList;

@Dao
public interface Contact_Dao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void create_contact(Contact contact);
    @Query("Delete from Contacts_Table")
    public void delete_all();
    @Query("Select * from contacts_table")
    public LiveData<ArrayList<Contact>> get_all();
}
