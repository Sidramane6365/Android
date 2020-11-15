package com.example.firebaseauth;

import android.widget.EditText;

public class UserhelperClass
{
    String name,address,email,password;
    Double lati,longi;
   public UserhelperClass(){}
    public UserhelperClass(String name,String address,String email,String password,Double lati,Double longi)
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

    public Double getLati() {
        return lati;
    }

    public void setLati(Double lati) {
        this.lati = lati;
    }

    public Double getLongi() {
        return longi;
    }

    public void setLongi(Double longi) {
        this.longi = longi;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
