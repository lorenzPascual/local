package com.example.juan_ted_ui;
import android.os.Bundle;
import android.util.Log;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.juan_ted_ui.util.ApolloConnector;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_messages, R.id.navigation_profile)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

            ApolloConnector.setupApollo().query(
                AllServiceTypeQuery
                        .builder()
                        .build())
                .enqueue(new ApolloCall.Callback<AllServiceTypeQuery.Data>() {


            @Override
            public void onResponse(@NotNull Response<AllServiceTypeQuery.Data> response) {

                Log.d("res",response.data().allServiceType().toString());

                for (AllServiceTypeQuery.AllServiceType services :response.data().allServiceType())
                {
                    Log.d("res",services.name.toString());
                } response.data().allServiceType();
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {
                Log.d("res",e.toString());
                Log.d("res",e.getMessage().toString());
            }
        });
        ApolloConnector.setupApollo().mutate(
                CustomerSignInMutation
                        .builder().email("customer-1@example.com").password("pass12345")
                        .build())
                .enqueue(new ApolloCall.Callback<CustomerSignInMutation.Data>() {


                    @Override
                    public void onResponse(@NotNull Response<CustomerSignInMutation.Data> response) {

                        Log.d("res",response.data().customerSignin.firstName);

                    }

                    @Override
                    public void onFailure(@NotNull ApolloException e) {
                        Log.d("res",e.toString());
                        Log.d("res",e.getMessage().toString());
                    }
                });
    }

}