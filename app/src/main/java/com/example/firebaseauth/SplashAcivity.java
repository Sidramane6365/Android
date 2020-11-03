package com.example.firebaseauth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import static java.lang.Thread.sleep;

public class SplashAcivity extends AppCompatActivity {
    ImageView imageView;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_acivity);

        imageView=findViewById(R.id.appImage);
        textView=findViewById(R.id.textname);
       Animation anim= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.animation);
        imageView.setAnimation(anim);
        textView.setAnimation(anim);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent=new Intent(SplashAcivity.this,Registration.class);
                startActivity(intent);
                SplashAcivity.this.finish();
            }
        }).start();
    }
}