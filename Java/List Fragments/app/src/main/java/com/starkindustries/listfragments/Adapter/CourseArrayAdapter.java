package com.starkindustries.listfragments.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.starkindustries.listfragments.Model.Course;
import com.starkindustries.listfragments.R;
import com.starkindustries.listfragments.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class CourseArrayAdapter extends ArrayAdapter<Course> {
    public Context context;
    public ArrayList<Course> course_list;
    public CourseArrayAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Course> course_list)
    {
        super(context, resource, course_list);
        context=this.context;
        course_list=this.course_list;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater =(LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.course_item,null);
        Course course = course_list.get(position);
        AppCompatImageView course_image = view.findViewById(R.id.course_image);
        course_image.setImageResource(course.getimageresourceis(context));
        AppCompatTextView course_name = view.findViewById(R.id.course_name);
        course_name.setText(course.getCoursename());
        return view;
    }
}
