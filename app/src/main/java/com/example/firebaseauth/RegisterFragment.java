package com.example.firebaseauth;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


public class RegisterFragment extends Fragment {



    public RegisterFragment() {
        // Required empty public constructor
    }

    TextView haveAnAccount;
    FrameLayout mainFrameLayout;
    private EditText name,address,email,password,confpassword;
    private Button regbutton;
    private FirebaseAuth firebaseAuth;
    private String emailpat="[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_register, container, false);
        haveAnAccount = view.findViewById(R.id.gotoLogin);
        mainFrameLayout=getActivity().findViewById(R.id.reg_frame);
        name=view.findViewById(R.id.regName);
        address=view.findViewById(R.id.regAddress);
        email=view.findViewById(R.id.loginEmail);
        password=view.findViewById(R.id.loginPassword);
        confpassword=view.findViewById(R.id.confirmPassword);
        regbutton=view.findViewById(R.id.registerButton);
        firebaseAuth=FirebaseAuth.getInstance();

        return view;
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        haveAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(new LoginFragmentone());
            }
        });


        name.addTextChangedListener(new TextWatcher() {
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
        email.addTextChangedListener(new TextWatcher() {
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
        password.addTextChangedListener(new TextWatcher() {
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
        confpassword.addTextChangedListener(new TextWatcher() {
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
        address.addTextChangedListener(new TextWatcher() {
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



        regbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //send data to firebase
                storedata();
                checkemail();
            }
        });
    }

    private void storedata() {
        FirebaseDatabase root=FirebaseDatabase.getInstance();
        DatabaseReference reference=root.getReference("Users");

        UserhelperClass adddata=new UserhelperClass(name.getText().toString(),address.getText().toString(),
                email.getText().toString(),password.getText().toString());
        reference.child(address.getText().toString()).setValue(adddata);
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction=getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(mainFrameLayout.getId(),fragment);
        fragmentTransaction.commit();


    }
    private  void checkUserInputs()
    {
        if(!TextUtils.isEmpty(name.getText()))
        {
            if(!TextUtils.isEmpty(email.getText())){
                if(!TextUtils.isEmpty(address.getText())){
                    if(!TextUtils.isEmpty(password.getText()) && password.length()>=8){
                        if(!TextUtils.isEmpty(confpassword.getText())){
                            regbutton.setEnabled(true);
                            regbutton.setTextColor(Color.rgb(255,255,255));

                        }else{
                            regbutton.setEnabled(false);
                            regbutton.setTextColor(Color.argb(50,255,255,255));
                        }
                    }else{
                        password.setError("Password must be at least of length 8!");
                        regbutton.setEnabled(false);
                        regbutton.setTextColor(Color.argb(50,255,255,255));
                    }
                }else{
                    regbutton.setEnabled(false);
                    regbutton.setTextColor(Color.argb(50,255,255,255));
                }
            }else{
                regbutton.setEnabled(false);
                regbutton.setTextColor(Color.argb(50,255,255,255));
            }
        }else{
            regbutton.setEnabled(false);
            regbutton.setTextColor(Color.argb(50,255,255,255));
        }

    }
    private  void checkemail()
    {
        if(email.getText().toString().matches(emailpat))
        {
            if(password.getText().toString().equals(confpassword.getText().toString()))
            {
                regbutton.setEnabled(false);
                regbutton.setTextColor(Color.argb(50,255,255,255));

                Toast.makeText(getContext(),"Please wait a while........",Toast.LENGTH_SHORT).show();


                firebaseAuth.createUserWithEmailAndPassword(email.getText().toString()
                        ,password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Intent intent=new Intent(getActivity(),SplashAcivity.class);
                            intent.putExtra("USER_mail",email.getText().toString());
                            intent.putExtra("USER_name",name.getText().toString());
                            intent.putExtra("USER_address",address.getText().toString());
                            startActivity(intent);
                            getActivity().finish();
                        }
                        else
                        {
                            regbutton.setEnabled(true);
                            regbutton.setTextColor(Color.rgb(255,255,255));
                            Toast.makeText(getActivity(),"Something went wrong. Already Registered",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }else {
                confpassword.setError("Password doesn't match!");
            }
        }else
        {
            email.setError("Invalid Email!");
        }
    }
    static String encodeUserEmail(String userEmail) {
        return userEmail.replace(".", ",");
    }

    static String decodeUserEmail(String userEmail) {
        return userEmail.replace(",", ".");
    }
}