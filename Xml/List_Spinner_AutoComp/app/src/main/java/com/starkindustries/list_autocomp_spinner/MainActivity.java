package com.starkindustries.list_autocomp_spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.starkindustries.list_autocomp_spinner.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(MainActivity.this,R.layout.activity_main);
        ArrayList<String> name_list = new ArrayList<String>();
        name_list.add("Aditya");
        name_list.add("Sandesj");
        name_list.add("Pranav");
        name_list.add("Mayur");
        name_list.add("Aaryamaan");
        name_list.add("Piyush");
        name_list.add("raj");
        name_list.add("Varad");
        name_list.add("Parth");
        name_list.add("Tanvir");
        name_list.add("parth");
        name_list.add("Nidish");
        name_list.add("reyansh");
        name_list.add("Vivek");
        name_list.add("Ranveer");
        name_list.add("kshitisj");
        name_list.add("Manan");
        ArrayAdapter<String> name_adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,name_list);
        binding.listview.setAdapter(name_adapter);
        binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, name_list.get(i), Toast.LENGTH_SHORT).show();
            }
        });
        ArrayList<String> department_list = new ArrayList<String>();
        department_list.add("Computer");
        department_list.add("IT");
        department_list.add("Electronics");
        department_list.add("Mechanical");
        department_list.add("Civil");
        department_list.add("Chemical");
        department_list.add("AI_DS");
        department_list.add("Mechatronics");
        department_list.add("Humanities");
        ArrayAdapter<String> department_adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,department_list);
        binding.department.setAdapter(department_adapter);
//        binding.department.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(MainActivity.this, binding.department.getSelectedItem().toString().trim(), Toast.LENGTH_SHORT).show();
//            }
//        });
//        binding.department.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent inext = new Intent(MainActivity.this, Second_Activity.class);
//                inext.putExtra("department",binding.department.getSelectedItem().toString().trim());
//                startActivity(inext);
//            }
//        });
        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inext = new Intent(MainActivity.this, Second_Activity.class);
                inext.putExtra("department",binding.department.getSelectedItem().toString().trim());
                startActivity(inext);
            }
        });

        ArrayList<String> department_name = new ArrayList<String>();
        department_name.add("Computer");
        department_name.add("IT");
        department_name.add("Electronics");
        department_name.add("Mechanical");
        department_name.add("Civil");
        department_name.add("Chemical");
        department_name.add("AI_DS");
        department_name.add("Mechatronics");
        department_name.add("Humanities");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,department_name);
        binding.autoComplete.setAdapter(adapter);
    }
}