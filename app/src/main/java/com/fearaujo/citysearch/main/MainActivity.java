package com.fearaujo.citysearch.main;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.fearaujo.citysearch.R;
import com.fearaujo.citysearch.main.items.ItemsFragment;
import com.fearaujo.model.City;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements SelectCityCallback {

    private SupportMapFragment mSupportMapFragment;
    private ItemsFragment mItemsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mItemsFragment = new ItemsFragment();
        mSupportMapFragment = SupportMapFragment.newInstance();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment, mItemsFragment);
        fragmentTransaction.addToBackStack(null);

        fragmentTransaction.commit();

    }

    @Override
    public void onSelect(City city) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(
                R.anim.enter_from_right, R.anim.exit_to_left,
                R.anim.enter_from_left, R.anim.exit_to_right
        );
        fragmentTransaction.replace(R.id.fragment, mSupportMapFragment);
        fragmentTransaction.addToBackStack(null);

        fragmentTransaction.commit();
        mSupportMapFragment.getMapAsync(googleMap -> {
            googleMap.addMarker(new MarkerOptions().position(
                    new LatLng(city.getCoord().getLat(), city.getCoord().getLon())
            ).title(city.getName()));


            double latitude = city.getCoord().getLat();
            double longitude = city.getCoord().getLon();

            CameraPosition camPos = new CameraPosition.Builder()
                    .target(new LatLng(latitude, longitude))
                    .zoom(13)
                    .build();

            CameraUpdate camUpd3 = CameraUpdateFactory.newCameraPosition(camPos);
            googleMap.animateCamera(camUpd3);
        });


    }
}
