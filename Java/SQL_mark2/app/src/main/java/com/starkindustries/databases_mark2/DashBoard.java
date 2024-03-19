package com.starkindustries.databases_mark2;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;

import com.starkindustries.databases_mark2.databinding.ActivityDashBoardBinding;

import java.util.ArrayList;

public class DashBoard extends AppCompatActivity {
    public ActivityDashBoardBinding binding;
    public DatabaseHandler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dash_board);
        binding= DataBindingUtil.setContentView(DashBoard.this,R.layout.activity_dash_board);
        handler=new DatabaseHandler(DashBoard.this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            ArrayList<User> user_list = new ArrayList<User>();
            user_list=handler.get_users_list();
            for(int i=0;i<user_list.size();i++)
                Log.d("users","Sid: "+user_list.get(i).getSid()+" Name: "+user_list
                        .get(i).getName());
            User user;
            user=(User) handler.get_user("2021FHCO054");
            Log.d("user_info","Name: "+user.getName()+" Username: "+user.getUsername()+" Phone no: "+user.getPhone_no() );
            Log.d("get_sid","Sid: "+handler.get_sid("Aditya Kelaskar"));
            binding.logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent inext = new Intent(DashBoard.this, Login_Screen.class);
                    startActivity(inext);
                }
            });
            return insets;
        });
    }
}