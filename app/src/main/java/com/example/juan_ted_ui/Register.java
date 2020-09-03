package com.example.juan_ted_ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.juan_ted_ui.services.CustomerServices;
import com.example.juan_ted_ui.util.GenericCallBack;

public class Register extends AppCompatActivity {
    EditText efirstname,lastname,email,password,mobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        efirstname = (EditText) findViewById(R.id.firstname);
        lastname = (EditText) findViewById(R.id.lastname);
        email = (EditText) findViewById(R.id.email);
        mobile = (EditText) findViewById(R.id.phone);
        password = (EditText) findViewById(R.id.userpass);

        Button btnregister = (Button) findViewById(R.id.btnregister);
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomerServices customerServices = new CustomerServices();
                customerServices.createCustomer(efirstname.getText().toString(), lastname.getText().toString(), email.getText().toString(), mobile.getText().toString(), password.getText().toString(), new GenericCallBack<CreateCustomerMutation.CreateCustomer>() {
                    @Override
                    public void success(CreateCustomerMutation.CreateCustomer createCustomer) {

                        Log.d("res", createCustomer.response.toString());
                    }
                });
            }
        });
    }
}