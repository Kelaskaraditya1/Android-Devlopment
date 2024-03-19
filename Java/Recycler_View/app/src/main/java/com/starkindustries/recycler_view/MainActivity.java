package com.starkindustries.recycler_view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.starkindustries.recycler_view.Adapter.RecyclerViewAdapter;
import com.starkindustries.recycler_view.Model.Contact;
import com.starkindustries.recycler_view.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    ArrayList<Contact> contact_list;
    AppCompatEditText contact_name,contact_number;
    AppCompatButton add_contact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding= DataBindingUtil.setContentView(MainActivity.this,R.layout.activity_main);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        contact_list=new ArrayList<Contact>();
        contact_list.add(new Contact(R.drawable.boy_one,"Aditya","8591059220"));
        contact_list.add(new Contact(R.drawable.boy_two,"Sandeep","9819375722"));
        contact_list.add(new Contact(R.drawable.boy_three,"Nidish","9654862510"));
        contact_list.add(new Contact(R.drawable.chutiya_one,"Nilesh","5864654778"));
        contact_list.add(new Contact(R.drawable.girl_one,"Rutika-Tai","6512457892"));
        contact_list.add(new Contact(R.drawable.girl_two,"Palak","95467871235"));
        contact_list.add(new Contact(R.drawable.girl_three,"Aakansha","9456312785"));
        contact_list.add(new Contact(R.drawable.man_one,"Deepak","5464789812"));
        contact_list.add(new Contact(R.drawable.user_logo,"Kaustubh","9819375722"));
        contact_list.add(new Contact(R.drawable.user_logo,"Mayur","6545829878"));
        contact_list.add(new Contact(R.drawable.boy_one,"Aditya","8591059220"));
        contact_list.add(new Contact(R.drawable.boy_two,"Sandeep","9819375722"));
        contact_list.add(new Contact(R.drawable.boy_three,"Nidish","9654862510"));
        contact_list.add(new Contact(R.drawable.chutiya_one,"Nilesh","5864654778"));
        contact_list.add(new Contact(R.drawable.girl_one,"Rutika-Tai","6512457892"));
        contact_list.add(new Contact(R.drawable.girl_two,"Palak","95467871235"));
        contact_list.add(new Contact(R.drawable.girl_three,"Aakansha","9456312785"));
        contact_list.add(new Contact(R.drawable.man_one,"Deepak","5464789812"));
        contact_list.add(new Contact(R.drawable.user_logo,"Kaustubh","9819375722"));
        contact_list.add(new Contact(R.drawable.user_logo,"Mayur","6545829878"));
        RecyclerViewAdapter contact_adapter = new RecyclerViewAdapter(MainActivity.this,contact_list);
        binding.recyclerView.setAdapter(contact_adapter);
        binding.openDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.add_dialog);
                contact_name=dialog.findViewById(R.id.name);
                contact_number=dialog.findViewById(R.id.number);
                add_contact=dialog.findViewById(R.id.add_contact);
                add_contact.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(!(contact_name.getText().toString().equals(" "))&&!(contact_number.getText().toString().equals(" ")))
                        {
                            contact_list.add(new Contact(contact_name.getText().toString().trim(),contact_number.getText().toString().trim()));
                            contact_adapter.notifyItemInserted(contact_list.size()-1);
                            binding.recyclerView.scrollToPosition(contact_list.size()-1);
                            InputMethodManager manager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                            manager.hideSoftInputFromWindow(view.getWindowToken(),0);
                            dialog.dismiss();
                        }
                    }
                });
                dialog.show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder exit_dialog = new AlertDialog.Builder(MainActivity.this);
        exit_dialog.setIcon(R.drawable.exit);
        exit_dialog.setTitle("Exit");
        exit_dialog.setMessage("Are you sure, you want to exit?");
        exit_dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MainActivity.super.onBackPressed();
            }
        });
        exit_dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "Welcome back", Toast.LENGTH_SHORT).show();
            }
        });
        exit_dialog.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        exit_dialog.show();
    }
}