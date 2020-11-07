package com.example.firebaseauth;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText hname,haddress,email,beds,cylinder;
    String _HNAME,_HADDRESS,_EMAIL;
    DatabaseReference reference;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hname=(EditText) findViewById(R.id.Hospname);
        haddress=(EditText)findViewById(R.id.Hospaddress);
        email=(EditText)findViewById(R.id.Hospemail);
        beds=(EditText)findViewById(R.id.Hospbed);
        cylinder=(EditText)findViewById(R.id.Hospcylinder);
        reference= FirebaseDatabase.getInstance().getReference("Users");

        showuserdata();
    }

    public void showuserdata()
    {
        Intent intent=getIntent();

        _EMAIL=intent.getStringExtra("USER_mail");

        _HNAME=intent.getStringExtra("USER_name");
        _HADDRESS=intent.getStringExtra("USER_address");
        hname.setText(_HNAME);
        haddress.setText("Hi");
        email.setText(_EMAIL);
    }
    public void update(View view)
    {
        if(isaddresschanged() || isNamechanged() || isEmailchanged())
        {
            Toast.makeText(this, "Data has been updated", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Data is same and can not be updated", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isEmailchanged() {
        if(! _EMAIL.equals(email.getText().toString()))
        {
            reference.child(_HNAME).child("email").setValue(email.getText().toString());
            _EMAIL=email.getText().toString();
            return true;
        }
        else
        {
            return false;
        }
    }

    private boolean isNamechanged()
    {
        if(! _HNAME.equals(hname.getText().toString()))
        {
            reference.child(_HNAME).child("name").setValue(hname.getText().toString());
            _HNAME=hname.getText().toString();
            return true;
        }
        else
        {
            return false;
        }
    }

    private boolean isaddresschanged()
    {
        if(! _HADDRESS.equals(haddress.getText().toString()))
        {
            reference.child(_HNAME).child("address").setValue(haddress.getText().toString());
            _HADDRESS=haddress.getText().toString();
            return true;

        }
        else
        {
            return false;
        }
    }
}