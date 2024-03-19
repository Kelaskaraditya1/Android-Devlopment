package com.starkindustries.alertdialogbox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.databinding.DataBindingUtil;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.starkindustries.alertdialogbox.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    public ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        setSupportActionBar(binding.toolbar);
        try {
            getSupportActionBar().setTitle("Alert Dialog");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        AlertDialog single_button_dialog = new AlertDialog.Builder(MainActivity.this).create();
        single_button_dialog.setIcon(R.drawable.user);
        single_button_dialog.setTitle("Log in");
        single_button_dialog.setMessage("Welcome User, Continue to Login");
        single_button_dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Log in", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "Log in Sucessfully", Toast.LENGTH_SHORT).show();
            }
        });
        binding.oneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                single_button_dialog.show();
            }
        });
        AlertDialog.Builder two_button_dialog = new AlertDialog.Builder(MainActivity.this);
        two_button_dialog.setIcon(R.drawable.delete);
        two_button_dialog.setTitle("Delete");
        two_button_dialog.setMessage("Are you sure,you want to delete");
        two_button_dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "Deleted Sucessfully", Toast.LENGTH_SHORT).show();
            }
        });
        two_button_dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "Delete Aborted", Toast.LENGTH_SHORT).show();
            }
        });
        binding.twoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                two_button_dialog.show();
            }
        });
        binding.threeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.order_layout);
                AppCompatButton ok = dialog.findViewById(R.id.ok);
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this,"I am Ironaman",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }

    @Override
    public void onBackPressed() {
//      super.onBackPressed();
        AlertDialog.Builder exit_dialog = new AlertDialog.Builder(MainActivity.this);
        exit_dialog.setIcon(R.drawable.baseline_add_home_24);
        exit_dialog.setTitle("Exit");
        exit_dialog.setMessage("Are you sure,you want to delete?");
        exit_dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MainActivity.super.onBackPressed();
            }
        });
        exit_dialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this,"Welcome back MF",Toast.LENGTH_LONG).show();
            }
        });
        exit_dialog.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
//        exit_dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(MainActivity.this).inflate(R.menu.tool_bar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id= item.getItemId();
        if(id==R.id.add_home)
            Toast.makeText(this, "Item Added Sucessfully", Toast.LENGTH_SHORT).show();
        else if(id==R.id.delete)
            Toast.makeText(this, "Item Deleted Sucessfully", Toast.LENGTH_SHORT).show();
        else if(id==R.id.user)
            Toast.makeText(this, "View User Proofile", Toast.LENGTH_SHORT).show();
        else if(id==android.R.id.home)
            onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}