package com.starkindustries.database;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.starkindustries.database.Database.DatabaseHandler;
import com.starkindustries.database.Model.Contact;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        try {
//            DatabaseHandler db = new DatabaseHandler(MainActivity.this);
//            Contact Aditya = new Contact("Aditya","8591059220");
//            Contact Sandeep = new Contact("Sandeep","9819375722");
//            Contact Sanjana = new Contact("Sanjana","9619074548");
//            db.add_contact(Aditya);
//            db.add_contact(Sandeep);
//           db.add_contact(Sanjana);
//        }
//        catch(Exception e)
//        {
//            e.printStackTrace();
//        }
//        ArrayList<Contact> contact_list = new ArrayList<Contact>();
        DatabaseHandler db = new DatabaseHandler(MainActivity.this);
//        contact_list=db.display_contacts();
//        for(Contact contact:contact_list)
//            Log.d("Contact",contact.getName()+" "+contact.getPhone_no());
//        Contact contact =(Contact) db.getContact(59);
//        if(contact!=null)
//        { Log.d("Contact",contact.getName()+" "+contact.getPhone_no());}
//        Contact contact = new Contact();
//                contact.setId(59);
//        contact.setName("Pramila");
//        contact.setPhone_no("9819375722");
//        Log.d("Contact",String.valueOf(db.contact_update(contact)));
//        db.delete_contact(59);
        Log.d("Contact_count","The Count of Contacts in the DataBase are "+String.valueOf(db.get_count()));
    }
}