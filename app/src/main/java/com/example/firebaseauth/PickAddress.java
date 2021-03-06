package com.example.firebaseauth;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class PickAddress extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap map;
    SupportMapFragment mapFragment;
    SearchView searchView;
    Button register,Change;
    String _EMAIL;
    Address address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_address);


        searchView=findViewById(R.id.search);
        mapFragment=(SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.gmapfrag);
        register=findViewById(R.id.gotoregister);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String location=searchView.getQuery().toString();
                List<Address> addressList=null;
                if(location!= null || !location.equals(""))
                {
                    Geocoder geocoder= new Geocoder(PickAddress.this);
                    try {
                        addressList=geocoder.getFromLocationName(location,1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    address = addressList.get(0);

                    LatLng latLng=new LatLng(address.getLatitude(),address.getLongitude());
                    map.addMarker(new MarkerOptions().position(latLng).title(address.toString().substring(24)));
                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        mapFragment.getMapAsync(this);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent data= new Intent();
                //set the value/data to pass back
                data.putExtra("Address",address.getAddressLine(0).toString());
                data.putExtra("Latitude", (address.getLatitude()));
                data.putExtra("Longitude",address.getLongitude());
                //set a result code, It is either RESULT_OK or RESULT_CANCELLED
                setResult(RESULT_OK,data);
                //Close the activity
                finish();
            }
        });

    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        map=googleMap;
    }



}