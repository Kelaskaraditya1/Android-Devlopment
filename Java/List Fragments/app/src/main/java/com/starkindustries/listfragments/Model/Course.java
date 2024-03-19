package com.starkindustries.listfragments.Model;

import android.content.Context;

public class Course
{
    public String coursename,courseimage;
    public Course(String coursename,String courseimage)
    {
        this.coursename=coursename;
        this.courseimage=courseimage;
    }
    public Course()
    {}

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getCourseimage() {
        return courseimage;
    }

    public void setCourseimage(String courseimage) {
        this.courseimage = courseimage;
    }
    public int getimageresourceis(Context context)
    {
        return context.getResources().getIdentifier(this.coursename,"drawable",context.getOpPackageName());
    }

}
