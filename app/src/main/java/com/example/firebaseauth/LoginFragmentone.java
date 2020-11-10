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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginFragmentone extends Fragment {


    public LoginFragmentone() {
        // Required empty public constructor
    }

    TextView gotoRegister;
    TextView gotomap,resetPass;
    FrameLayout mainFramelayout;
    private EditText useremail,userpass;
    private Button loginbtn;
    private FirebaseAuth firebaseAuth;
    private String emailpat="[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_login_fragmentone, container, false);
        gotoRegister=view.findViewById(R.id.gotoSignin);
        gotomap=view.findViewById(R.id.gotoMap);
        resetPass=view.findViewById(R.id.forgotPassword);
        mainFramelayout=getActivity().findViewById(R.id.reg_frame);
        useremail=view.findViewById(R.id.userEmail);
        userpass=view.findViewById(R.id.userPassword);
        loginbtn=view.findViewById(R.id.loginButton);
        firebaseAuth=FirebaseAuth.getInstance();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        //check email and password are valid or not
        useremail.addTextChangedListener(new TextWatcher() {
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
        userpass.addTextChangedListener(new TextWatcher() {
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
        //After clicking on sign up where to go
        gotoRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(new RegisterFragment());
            }
        });
        //After clicking on Skip where to go
        gotomap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),GMapActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        //After clicking on forgot password where to go
        resetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(new ResetPassword());
            }
        });
        //After clicking on Login btn where to go
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkemail();
            }
        });
    }
    //To change fragment
    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction=getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(mainFramelayout.getId(),fragment);
        fragmentTransaction.commit();
    }
    private  void checkUserInputs()
    {

        if(!TextUtils.isEmpty(useremail.getText())){

            if(!TextUtils.isEmpty(userpass.getText()) && userpass.length()>=8){
                loginbtn.setEnabled(true);
                loginbtn.setTextColor(Color.rgb(255,255,255));
            }
            else{
                loginbtn.setEnabled(false);
                loginbtn.setTextColor(Color.argb(50,255,255,255));
            }
        }
        else{
            loginbtn.setEnabled(false);
            loginbtn.setTextColor(Color.argb(50,255,255,255));
        }


    }
    private  void checkemail()
    {
        if(useremail.getText().toString().matches(emailpat))
        {
            if(userpass.length()>=8) {

                Toast.makeText(getContext(),"Please wait a while........",Toast.LENGTH_SHORT).show();


                firebaseAuth.signInWithEmailAndPassword(useremail.getText().toString()
                        , userpass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Intent intent=new Intent(getActivity(),MainActivity.class);
                            intent.putExtra("USER_mail",useremail.getText().toString());
                            startActivity(intent);
                            getActivity().finish();
                        }
                        else
                        {
                            loginbtn.setEnabled(true);
                            loginbtn.setTextColor(Color.rgb(255,255,255));
                            Toast.makeText(getActivity(),"Something went wrong",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
            else {
                Toast.makeText(getActivity(),"Wrong Password!!!",Toast.LENGTH_SHORT).show();
            }
        }else
        {
            Toast.makeText(getActivity(),"Wrong Email!!!",Toast.LENGTH_SHORT).show();
        }
    }
}