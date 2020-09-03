package com.example.juan_ted_ui.services;

import android.util.Log;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.juan_ted_ui.CreateCustomerMutation;
import com.example.juan_ted_ui.CustomerLocationQuery;
import com.example.juan_ted_ui.UpdateCustomerInfoMutation;
import com.example.juan_ted_ui.util.ApolloConnector;
import com.example.juan_ted_ui.util.GenericCallBack;

import org.jetbrains.annotations.NotNull;

public class CustomerServices {

    public void getCustomerLoc(int ID,GenericCallBack<CustomerLocationQuery.Customer> callBack){
        ApolloConnector.setupApollo().query(
                CustomerLocationQuery
                        .builder().customer_id(ID)
                        .build())
                .enqueue(new ApolloCall.Callback<CustomerLocationQuery.Data>() {

                    @Override
                    public void onResponse(@NotNull Response<CustomerLocationQuery.Data> response) {


                            CustomerLocationQuery.Customer customer= response.data().customer().get(0);

                            callBack.success(customer);

                    }

                    @Override
                    public void onFailure(@NotNull ApolloException e) {
                        Log.d("res",e.toString());
                        Log.d("res",e.getMessage().toString());
                    }
                });
    }
    public void updateCustomerInfo(int id, String firstname, String lastname, String email, String mobilenumber, GenericCallBack<UpdateCustomerInfoMutation.UpdateCustomerInfo> callBack){
        ApolloConnector.setupApollo().mutate(
                UpdateCustomerInfoMutation
                        .builder().customer_id(id).first_name(firstname).last_name(lastname).email(email).mobile_number(mobilenumber)
                        .build())
                .enqueue(new ApolloCall.Callback<UpdateCustomerInfoMutation.Data>() {

                    @Override
                    public void onResponse(@NotNull Response<UpdateCustomerInfoMutation.Data> response) {


                        UpdateCustomerInfoMutation.UpdateCustomerInfo customer= response.data().updateCustomerInfo();

                        callBack.success(customer);

                    }

                    @Override
                    public void onFailure(@NotNull ApolloException e) {
                        Log.d("res",e.toString());
                        Log.d("res",e.getMessage().toString());
                    }
                });
    }

    public void createCustomer(String firstname, String lastname, String email, String mobilenumber, String password, GenericCallBack<CreateCustomerMutation.CreateCustomer> callBack){
        ApolloConnector.setupApollo().mutate(
                CreateCustomerMutation
                        .builder().first_name(firstname).last_name(lastname).email(email).mobile_number(mobilenumber).password(password)
                        .build())
                .enqueue(new ApolloCall.Callback<CreateCustomerMutation.Data>() {

                    @Override
                    public void onResponse(@NotNull Response<CreateCustomerMutation.Data> response) {


                        CreateCustomerMutation.CreateCustomer customer= response.data().createCustomer();

                        callBack.success(customer);

                    }

                    @Override
                    public void onFailure(@NotNull ApolloException e) {
                        Log.d("res",e.toString());
                        Log.d("res",e.getMessage().toString());
                    }
                });
    }

}
