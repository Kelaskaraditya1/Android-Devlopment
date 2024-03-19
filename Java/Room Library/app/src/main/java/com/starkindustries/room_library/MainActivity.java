package com.starkindustries.room_library;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.RoomDatabase;

import android.os.Bundle;
import android.util.Log;

import com.starkindustries.room_library.Model.Contact;
import com.starkindustries.room_library.Model.ContactModel;

public class MainActivity extends AppCompatActivity {
    public ContactModel contactModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contactModel=new ViewModelProvider.AndroidViewModelFactory(MainActivity.this.getApplication()).create(ContactModel.class);
        contactModel.get_contacts().observe(MainActivity.this,contacts -> {
            Log.d("Contacts","The Name of the Contact is "+contacts.get(1).getName());
        });
    }
}