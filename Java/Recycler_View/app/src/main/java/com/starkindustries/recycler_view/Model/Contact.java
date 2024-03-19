package com.starkindustries.recycler_view.Model;

public class Contact
{
 public int image;
 public String name,number;
 public Contact(int image,String name,String number)
 {
     this.image=image;
     this.name=name;
     this.number=number;
 }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Contact(String name, String number)
 {
     this.name=name;
     this.number=number;
 }
    public Contact()
    {}

}
