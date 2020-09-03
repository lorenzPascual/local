package com.example.juan_ted_ui.customer.servicetypes;

import android.util.Log;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.MutableLiveData;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.juan_ted_ui.AllServiceTypeQuery;
import com.example.juan_ted_ui.util.ApolloConnector;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ServiceTypesViewModel extends ViewModel {

    MutableLiveData<ArrayList<ServiceType>> serviceLiveData;
    ArrayList<ServiceType> serviceArrayList;


    public ServiceTypesViewModel() {
        serviceLiveData = new MutableLiveData<>();
        init();
    }
    public void init(){
        populateList();
    }

    public MutableLiveData<ArrayList<ServiceType>> getUserMutableLiveData() {
        return serviceLiveData;
    }


    public void populateList(){

        serviceArrayList = new ArrayList<>();
        ApolloConnector.setupApollo().query(
                AllServiceTypeQuery
                        .builder()
                        .build())
                .enqueue(new ApolloCall.Callback<AllServiceTypeQuery.Data>() {
                    @Override
                    public void onResponse(@NotNull Response<AllServiceTypeQuery.Data> response) {
                        List<AllServiceTypeQuery.AllServiceType> allServiceTypes = response.data().allServiceType();
                        for (AllServiceTypeQuery.AllServiceType servicetypes:
                        allServiceTypes) {
                            ServiceType service = new ServiceType();

                            service.setId(Long.parseLong(servicetypes.id()));
                            service.setName(servicetypes.name());
                            service.setImage(servicetypes.image());

                            serviceArrayList.add(service);
                        }

                        serviceLiveData.postValue(serviceArrayList);
                    }

                    @Override
                    public void onFailure(@NotNull ApolloException e) {
                        Log.d("res",e.toString());
                        Log.d("res",e.getMessage().toString());
                    }
                });
    }
}
