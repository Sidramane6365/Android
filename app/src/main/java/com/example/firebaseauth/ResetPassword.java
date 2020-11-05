package com.example.firebaseauth;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class ResetPassword extends Fragment {

    public ResetPassword() {
        // Required empty public constructor
    }
    FrameLayout mainFramelayout;
    private EditText fpemail;
private Button fpbtn;
    private String emailpat="[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";
private FirebaseAuth firebaseAuth;
 @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_reset_password, container, false);
        fpemail=view.findViewById(R.id.fpEmail);
        fpbtn=view.findViewById(R.id.fpButton);
        firebaseAuth=FirebaseAuth.getInstance();
     mainFramelayout=getActivity().findViewById(R.id.reg_frame);
        return view;
        // Inflate the layout for this fragment

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                     checkmail();
                }
        });
    }
    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction=getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(mainFramelayout.getId(),fragment);
        fragmentTransaction.commit();
 }
 private void checkmail() {
     if (fpemail.getText().toString().matches(emailpat)) {
     Toast.makeText(getContext(), "Link send to email", Toast.LENGTH_SHORT).show();
      firebaseAuth.sendPasswordResetEmail(fpemail.getText().toString()
         ).addOnCompleteListener(new OnCompleteListener<Void>() {
             @Override
             public void onComplete(@NonNull Task<Void> task) {

                 if (task.isSuccessful()) {

                     setFragment(new LoginFragmentone());
                 } else {

                     Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
                 }
             }


         });
     }
     else
     {
         Toast.makeText(getActivity(),"Wrong Email!!!",Toast.LENGTH_SHORT).show();
     }
 }
}
