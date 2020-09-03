package com.example.juan_ted_ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.juan_ted_ui.customer.CustomerDashboard;
import com.example.juan_ted_ui.tasker.TaskerDashboard;
import com.example.juan_ted_ui.util.ApolloConnector;

import org.jetbrains.annotations.NotNull;

public class LoginActivity extends AppCompatActivity {
    EditText users, passs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        users = (EditText) findViewById(R.id.user);
        passs = (EditText) findViewById(R.id.pass);
    }
    public void Signup(View v) {
        Intent intent = new Intent(getBaseContext(), Register.class);
        startActivity(intent);
    }
    public void Login(View v) {
        String sUser = users.getText().toString();
        String sPass = passs.getText().toString();

        ApolloConnector.setupApollo().mutate(
                CustomerSignInMutation
                        .builder().email(sUser).password(sPass)
                        .build())
                .enqueue(new ApolloCall.Callback<CustomerSignInMutation.Data>() {

                    @Override
                    public void onResponse(@NotNull Response<CustomerSignInMutation.Data> response) {
                        if(response.data().equals(null)) {
                            Toast.makeText(getBaseContext(),"loginfailed",Toast.LENGTH_SHORT).show();
                        } else {
                            CustomerSignInMutation.CustomerSignin customer = response.data().customerSignin;
                            Log.d("res", response.data().customerSignin.firstName);
                            SharedPreferences shared = getSharedPreferences("account", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = shared.edit();
                            editor.putInt("userId",Integer.valueOf(customer.id));
                            editor.putString("userName",customer.email );
                            editor.putString("userFirstName",customer.firstName);
                            editor.putString("userLastName",customer.lastName);

                            editor.apply();
                            editor.commit();
                            Intent intent = new Intent(getBaseContext(), TaskerDashboard.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(@NotNull ApolloException e) {
                        Log.d("res",e.toString());
                        Log.d("res",e.getMessage().toString());
                    }
                });

//                    try {
//                        JSONObject jArray = new JSONObject(s);
//
//                        String id = jArray.getString("id");
//                        name = jArray.getString("username");
//                        String type = jArray.getString("type");
//                        if (type.equals("customer")) {
//                            name = jArray.getString("fullname");
//                        } else {
//                            name = jArray.getString("companyName");
//                        }
////                        String add = jArray.getString("address");
//
////                        String email = jArray.getString("Email");
////                        String address = jArray.getString("Contact_Number");
//                        //   Toast.makeText(getBaseContext(), id+name+email, Toast.LENGTH_SHORT).show();
//
//                        SharedPreferences shared = getSharedPreferences("account", Context.MODE_PRIVATE);
//                        SharedPreferences.Editor editor = shared.edit();
//                        editor.putString("userId", id);
//                        editor.putString("userName", name);
//                        editor.putString("type", type);
//                        editor.putString("OnHome", "");
////                        editor.putString("userAddress",add);
//
//                        editor.apply();
//                        editor.commit();
//                        if (type.equals("customer")) {
//                            Intent intent = new Intent(getBaseContext(), MapsActivityCurrentPlace.class);
//                            startActivity(intent);
//                        } else {
//                            Intent intent = new Intent(getBaseContext(), CompanyActivity.class);
//                            startActivity(intent);
//                        }
//                        finish();
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }

    }
}