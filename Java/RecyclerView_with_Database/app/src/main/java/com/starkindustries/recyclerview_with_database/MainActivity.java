    package com.starkindustries.recyclerview_with_database;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.starkindustries.recyclerview_with_database.Database.DatabaseHandler;
import com.starkindustries.recyclerview_with_database.Model.Contacts;
import com.starkindustries.recyclerview_with_database.databinding.ActivityContactListBinding;
import com.starkindustries.recyclerview_with_database.databinding.ActivityMainBinding;

import java.util.ArrayList;

    public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    ActivityContactListBinding binding2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding= DataBindingUtil.setContentView(MainActivity.this,R.layout.activity_main);
        DatabaseHandler handler = new DatabaseHandler(MainActivity.this);
        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=binding.name.getText().toString().trim();
                String number=binding.number.getText().toString().trim();
                DatabaseHandler db = new DatabaseHandler(MainActivity.this);
                db.add_contact(new Contacts(name,number));
                InputMethodManager manager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                manager.hideSoftInputFromWindow(view.getWindowToken(),0);
                binding2 = DataBindingUtil.setContentView(MainActivity.this,R.layout.activity_contact_list);
                Intent inext = new Intent(MainActivity.this, Contact_List.class);
                int position=handler.getcount();
                startActivity(inext);
                binding2.recyclerView.scrollToPosition(position);
                Log.d("Contact_count","The Count is "+handler.getcount());
            }
        });
        binding.viewContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inext = new Intent(MainActivity.this, Contact_List.class);
                startActivity(inext);
            }
        });
//        Contacts Aditya = new Contacts("Aditya","8591059220");
//        handler.add_contact(Aditya);
//        Contacts Sandeep = new Contacts("Sandeep","9819375722");
//        handler.add_contact(Sandeep);
//        Contacts Sanjana = new Contacts("Sanjana","9619074548");
//        handler.add_contact(Sanjana);
//        Contacts contacts = (Contacts) handler.select_contact(1);
//        try {
//            if (contacts != null)
//                Log.d("Contact", "The Name is " + contacts.getName());
//            else
//                Log.d("Contact", "Error in reciving contact");
//        }
//        catch(Exception e)
//        {
//            e.printStackTrace();
//        }

//        for(int i=0;i<contact_list.size();i++)
//            handler.delete_contact(i);
    }
}