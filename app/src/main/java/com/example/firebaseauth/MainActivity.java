package com.example.firebaseauth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText hname,haddress,email,beds,cylinder;
    String _HNAME,_HADDRESS,_EMAIL,Dotemail;
    Button save;
    DatabaseReference reference;
    FirebaseAuth firebaseAuth;

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
        save=findViewById(R.id.Hospsave);

        firebaseAuth=FirebaseAuth.getInstance();
        showuserdata();
        beds.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkUserInputs();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        cylinder.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkUserInputs();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void checkUserInputs() {
        if(!TextUtils.isEmpty(beds.getText())){
            if(!TextUtils.isEmpty(cylinder.getText())){
                save.setEnabled(true);
                save.setTextColor(Color.rgb(255,255,255));

            }else{
                save.setEnabled(false);
                save.setTextColor(Color.argb(50,255,255,255));
            }
        }else{

            save.setEnabled(false);
            save.setTextColor(Color.argb(50,255,255,255));
        }
    }

    public void showuserdata()
    {
        Intent intent=getIntent();
        _EMAIL=intent.getStringExtra("USER_mail");
        Dotemail=encodeUserEmail(_EMAIL);
        reference= FirebaseDatabase.getInstance().getReference("Users").child(Dotemail);


        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                _HNAME=snapshot.child("name").getValue().toString();
                _HADDRESS=snapshot.child("address").getValue().toString();
                hname.setText(_HNAME);
                haddress.setText(_HADDRESS);
                email.setText(_EMAIL);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
    public void update(View view)
    {

            reference.child("name").setValue(hname.getText().toString());
            _HNAME=hname.getText().toString();
            reference.child("email").setValue(email.getText().toString());
            _EMAIL=email.getText().toString();
            reference.child("address").setValue(haddress.getText().toString());
            _HADDRESS=haddress.getText().toString();
            reference.child("beds").setValue(beds.getText().toString());
            reference.child("cylinders").setValue(cylinder.getText().toString());
            Toast.makeText(this, "Data has been updated", Toast.LENGTH_SHORT).show();


    }


    static String encodeUserEmail(String userEmail) {
        return userEmail.replace(".", ",");
    }

    static String decodeUserEmail(String userEmail) {
        return userEmail.replace(",", ".");
    }
}