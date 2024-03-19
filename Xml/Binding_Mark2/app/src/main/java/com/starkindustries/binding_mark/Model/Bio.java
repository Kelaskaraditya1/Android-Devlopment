package com.starkindustries.binding_mark.Model;

public class Bio {
    public String name;
    public String hobby;
    public Bio()
    {}
    public Bio(String name,String hobby)
    {
        this.name=name;
        this.hobby=hobby;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }
}
