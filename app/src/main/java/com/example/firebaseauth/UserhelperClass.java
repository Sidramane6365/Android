package com.example.firebaseauth;

import android.widget.EditText;

public class UserhelperClass
{
    String name,address,email,password,lati,longi;
   public UserhelperClass(){}
    public UserhelperClass(String name,String address,String email,String password,String lati,String longi)
    {
        this.name=name;
        this.address=address;
        this.email=email;
        this.password=password;
        this.lati=lati;
        this.longi=longi;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLati() {
        return lati;
    }

    public String getLongi() {
        return longi;
    }

    public void setLongi(String longi) {
        this.longi = longi;
    }

    public void setLati(String lati) {
        this.lati = lati;
    }
}
