package com.example.staffinemployees.Maps;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.example.staffinemployees.Interface.BottomSheetListener;
import com.example.staffinemployees.R;
import com.example.staffinemployees.databinding.FragmentMapFragBottomSheetBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapFragBottomSheet extends BottomSheetDialogFragment implements OnMapReadyCallback {
    FragmentMapFragBottomSheetBinding binding;
    private BottomSheetListener mListener;

    Context context;
    FusedLocationProviderClient fusedLocationProviderClient;
    private GoogleMap mMap;
    private Marker mMarker;

    public MapFragBottomSheet(Context context) {
        this.context = context;
    }

    String address;
    String userLatitude;
    String userLongitude;
    double latitude;
    double longitude;
    double finalLat;
    double finalLong;


    Geocoder geocoder;
    List<Address> addresses;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMapFragBottomSheetBinding.inflate(inflater, container, false);
        geocoder = new Geocoder(getContext(), Locale.getDefault());
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapBottom);
        mapFragment.getMapAsync(this);
        mapFragment.getView().setClickable(false);
        mapFragment.getView().setFocusable(false);
        userLatitude = getArguments().getString("latitude");
        userLongitude = getArguments().getString("longitude");

        latitude = Double.parseDouble(userLatitude);
        longitude = Double.parseDouble(userLongitude);


        binding.submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    addresses = geocoder.getFromLocation(finalLat, finalLong, 1);
                    if (!addresses.isEmpty()) {
                        address = addresses.get(0).getAddressLine(0) + addresses.get(0).getLocality();
                        Log.d("addressAddress", address);
                    } else {
                        address = "no address found , try again";
                        Log.d("addressAddress", address);
                    }
                } catch (IOException e) {
                    Log.e("Geocoder", "Error: " + e.getMessage());
                    e.printStackTrace();
                } finally {
                    dismiss();
                }

            }
        });
        return binding.getRoot();

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mListener = (BottomSheetListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement BottomSheetListener");
        }
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        mListener.onTextSelected(address);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 0);
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public boolean onMyLocationButtonClick() {
                fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            // Move camera to current location
                            LatLng currentLatLng = new LatLng(location.getLatitude(), location.getLongitude());
                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15));
                        }
                    }
                });
                return false;
            }
        });
        LatLng sydney = new LatLng(latitude, longitude);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15f));

        LatLng center = mMap.getCameraPosition().target;
        BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.marker);
        mMarker = mMap.addMarker(new MarkerOptions().position(center).icon(icon).anchor(0.1f, 0.1f));

        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        // Set a move listener on the map to move the marker to the center of the screen when the map moves
        mMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
            @Override
            public void onCameraMove() {
                LatLng center = mMap.getCameraPosition().target;
                mMarker.setPosition(center);

                finalLat = center.latitude;
                finalLong = center.longitude;
//                try {
//
//                    addresses = geocoder.getFromLocation(center.latitude, center.longitude, 1);
//                    if (!addresses.isEmpty()) {
//                        binding.currentLocationTv.setText(addresses.get(0).getAddressLine(0) + addresses.get(0).getLocality());
//                    } else {
//                        address = "no address found , try again";
//                        Log.d("addressAddress", address);
//                    }
//                } catch (IOException e) {
//                    Log.e("Geocoder", "Error: " + e.getMessage());
//                    e.printStackTrace();
//                }
            }
        });
    }
}


