package com.starkindustries.room_library.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Contacts_Table")
public class Contact {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name="Customer_Name")
    public String name;
    @ColumnInfo(name="Customers_Phoneno")
    public String phone_no;
    public Contact(int id,String name,String phone_no)
    {
        this.id=id;
        this.name=name;
        this.phone_no=phone_no;
    }
    public Contact(String name,String phone_no)
    {
        this.name=name;
        this.phone_no=phone_no;
    }

    public Contact()
    {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }
}
