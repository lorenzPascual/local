package com.example.juan_ted_ui.customer.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.juan_ted_ui.CustomerLocationQuery;
import com.example.juan_ted_ui.R;
import com.example.juan_ted_ui.UpdateCustomerGeolocationMutation;
import com.example.juan_ted_ui.customer.services.ServicesFragmentDirections;
import com.example.juan_ted_ui.services.CustomerServices;
import com.example.juan_ted_ui.util.ApolloConnector;
import com.example.juan_ted_ui.util.GenericCallBack;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

import static android.content.ContentValues.TAG;

public class ChooseLocation extends Fragment {


    GoogleMap mMap;

    public Marker myMarker =  null;
    // A default location (Sydney, Australia) and default zoom to use when location permission is
    // not granted.
    private final LatLng mDefaultLocation = new LatLng(-33.8523341, 151.2106085);
    private static final int DEFAULT_ZOOM = 15;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private boolean mLocationPermissionGranted;

    // The geographical location where the device is currently located. That is, the last-known
    // location retrieved by the Fused Location Provider.
    private Location mLastKnownLocation;

    // Keys for storing activity state.
    private static final String KEY_CAMERA_POSITION = "camera_position";
    private static final String KEY_LOCATION = "location";

    // The entry point to the Fused Location Provider.
    private FusedLocationProviderClient mFusedLocationProviderClient;

    private LatLng selectedlatLng;
    private String formattedaddress;
    private View v;

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;
            LatLng sydney = new LatLng(-34, 151);
            mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            getLocationPermission();
            getDeviceLocation();


        }
    };


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_customer_home_choose_location, container, false);
        Button btngotoschedpicker = (Button) v.findViewById(R.id.btngotosched);
        btngotoschedpicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action =
                        ChooseLocationDirections
                                .navigateToSchedpicker();
                Navigation.findNavController(view).navigate(action);
            }
        });
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedPreferences shared =  getContext().getSharedPreferences("account", Context.MODE_PRIVATE);
        int sid = shared.getInt("userId",1);
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);

        }
        try {

            CustomerServices customerServices = new CustomerServices();
            customerServices.getCustomerLoc(sid,new GenericCallBack<CustomerLocationQuery.Customer>() {
                @Override
                public void success(CustomerLocationQuery.Customer customer) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                    new LatLng(Double.valueOf(customer.lat()),
                                            Double.valueOf(customer.lng())), DEFAULT_ZOOM));
                            selectedlatLng = new LatLng(Double.valueOf(customer.lat()),
                                    Double.valueOf(customer.lng()));
                            formattedaddress = customer.formattedAddress();
                            myMarker.setPosition(selectedlatLng);
                        }
                    });

                }
            });
        }catch (Exception e){

        }

        String apiKey = getString(R.string.api_key);

        /**
         * Initialize Places. For simplicity, the API key is hard-coded. In a production
         * environment we recommend using a secure mechanism to manage API keys.
         */
        if (!Places.isInitialized()) {
            Places.initialize(getContext(), apiKey);
        }

        // Create a new Places client instance.
        PlacesClient placesClient = Places.createClient(getActivity());
        // Initialize the AutocompleteSupportFragment.
        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getChildFragmentManager().findFragmentById(R.id.autocomplete_fragment);

        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG, Place.Field.ADDRESS));

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i(TAG, "Place: " + place.getName() + ", " + place.getId());

                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                        new LatLng(place.getLatLng().latitude,
                                place.getLatLng().longitude), DEFAULT_ZOOM));
                selectedlatLng = place.getLatLng();
                formattedaddress = place.getAddress();
                myMarker.setPosition(place.getLatLng());

            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        });

    }

    private void getDeviceLocation() {
        /*
         * Get the best and most recent location of the device, which may be null in rare
         * cases when a location is not available.
         */
        final Interpolator interpolator = new LinearInterpolator();
        int height = 80;
        int width = 78;
//        BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(R.drawable.car2);
//        Bitmap b=bitmapdraw.getBitmap();
//        final Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);

        try {
            if (mLocationPermissionGranted) {
                Task<Location> locationResult = mFusedLocationProviderClient.getLastLocation();
                locationResult.addOnCompleteListener(getActivity(), new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if (task.isSuccessful()) {
                            // Set the map's camera position to the current location of the device.
                            mLastKnownLocation = task.getResult();
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                    new LatLng(mLastKnownLocation.getLatitude(),
                                            mLastKnownLocation.getLongitude()), DEFAULT_ZOOM));

                            myMarker = mMap.addMarker(new MarkerOptions()
                                    .title("Me")
                                    .position(new LatLng(mLastKnownLocation.getLatitude(),
                                            mLastKnownLocation.getLongitude())));


                        } else {
                            Log.d(TAG, "Current location is null. Using defaults.");
                            Log.e(TAG, "Exception: %s", task.getException());
                            mMap.moveCamera(CameraUpdateFactory
                                    .newLatLngZoom(mDefaultLocation, DEFAULT_ZOOM));
                            mMap.getUiSettings().setMyLocationButtonEnabled(false);
                        }
                    }
                });
            }
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }
    private void getLocationPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(this.getContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }
}