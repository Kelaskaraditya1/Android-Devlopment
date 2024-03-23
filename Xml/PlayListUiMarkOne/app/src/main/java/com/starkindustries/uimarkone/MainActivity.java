package com.starkindustries.uimarkone;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;

import com.starkindustries.uimarkone.databinding.ActivityMainBinding;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    public ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        binding= DataBindingUtil.setContentView(MainActivity.this,R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            Calendar calender = Calendar.getInstance();
            int year=calender.get(Calendar.YEAR);
            int month=calender.get(Calendar.MONTH);
            int date=calender.get(Calendar.DATE);
            int day = calender.get(Calendar.DAY_OF_MONTH);
            int hour=calender.get(Calendar.HOUR);
            int min=calender.get(Calendar.MINUTE);
            int sec=calender.get(Calendar.SECOND);
            binding.calender.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatePickerDialog dialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            binding.calender.setText("Month: "+month+" Year: "+year+" Date: "+dayOfMonth);
                        }
                    },year,month,day);
                    dialog.show();
                }
            });
            binding.time.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TimePickerDialog dialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            binding.time.setText("Hour: "+hour+" Minute: "+minute);
                        }
                    },hour,min,false);
                    dialog.show();
                }
            });
            return insets;
        });
    }
}