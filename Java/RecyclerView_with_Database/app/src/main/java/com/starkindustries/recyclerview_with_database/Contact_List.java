package com.starkindustries.recyclerview_with_database;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.starkindustries.recyclerview_with_database.Adapter.RecyclerViewAdapter;
import com.starkindustries.recyclerview_with_database.Database.DatabaseHandler;
import com.starkindustries.recyclerview_with_database.Model.Contacts;
import com.starkindustries.recyclerview_with_database.databinding.ActivityContactListBinding;

import java.util.ArrayList;

public class Contact_List extends AppCompatActivity {
    ActivityContactListBinding binding;
    public ArrayList<Contacts> contact_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        binding= DataBindingUtil.setContentView(Contact_List.this,R.layout.activity_contact_list);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(Contact_List.this));
        DatabaseHandler handler = new DatabaseHandler(Contact_List.this);
        try {
            contact_list = handler.get_contacts();
            RecyclerViewAdapter contact_adapter = new RecyclerViewAdapter(Contact_List.this,contact_list);
            binding.recyclerView.setAdapter(contact_adapter);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}