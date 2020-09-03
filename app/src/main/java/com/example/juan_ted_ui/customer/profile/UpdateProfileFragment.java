package com.example.juan_ted_ui.customer.profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.juan_ted_ui.R;
import com.example.juan_ted_ui.UpdateCustomerInfoMutation;
import com.example.juan_ted_ui.services.CustomerServices;
import com.example.juan_ted_ui.util.GenericCallBack;


public class UpdateProfileFragment extends Fragment {

    EditText efirstname,lastname,email,mobile;
    View v;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_customer_profile_update_profile, container, false);
        SharedPreferences shared =  getContext().getSharedPreferences("account", Context.MODE_PRIVATE);
        int sid = shared.getInt("userId",0);
        efirstname = (EditText) v.findViewById(R.id.firstname);
        lastname = (EditText) v.findViewById(R.id.lastname);
        email = (EditText) v.findViewById(R.id.email);
        mobile = (EditText) v.findViewById(R.id.phone);
        Button btnupdate = (Button) v.findViewById(R.id.btnupdate);
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomerServices customerServices = new CustomerServices();
                customerServices.updateCustomerInfo(1, efirstname.getText().toString(), lastname.getText().toString(), email.getText().toString(), mobile.getText().toString(), new GenericCallBack<UpdateCustomerInfoMutation.UpdateCustomerInfo>() {

                    @Override
                    public void success(UpdateCustomerInfoMutation.UpdateCustomerInfo updateCustomerInfo) {

                    }
                });
            }
        });
        return v;
    }
}