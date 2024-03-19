package com.starkindustries.room_library;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Expenses_Table")
public class Expenses
{
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "Item")
    public String item;
    @ColumnInfo(name = "Price")
    public String price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Expenses(int id, String item,String price)
    {
        this.id=id;
        this.item=item;
        this.price=price;
    }
    public Expenses(String item,String price)
    {
        this.item=item;
        this.price=price;
    }
    public Expenses()
    {}

}
