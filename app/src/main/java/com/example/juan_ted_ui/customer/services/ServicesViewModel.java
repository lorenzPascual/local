package com.example.juan_ted_ui.customer.services;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.juan_ted_ui.ServiceQuery;
import com.example.juan_ted_ui.util.ApolloConnector;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ServicesViewModel extends ViewModel {

    MutableLiveData<ArrayList<Service>> serviceLiveData;
    ArrayList<Service> serviceArrayList;
    private MutableLiveData<Long> ID;
    public ServicesViewModel() {
        serviceLiveData = new MutableLiveData<>();
        ID = new MutableLiveData<>();
//        init();
    }
    public void init(){
        populateList();
    }

    public MutableLiveData<ArrayList<Service>> getUserMutableLiveData() {
        return serviceLiveData;
    }

    public LiveData<Long> getID() {
        return ID;
    }
    public void setID(long id) {
        ID.setValue(id);
        populateList();
    }
    public void populateList(){


        serviceArrayList = new ArrayList<>();
        ApolloConnector.setupApollo().query(
                ServiceQuery
                        .builder().service_type_id(ID.getValue().intValue())
                        .build())
                .enqueue(new ApolloCall.Callback<ServiceQuery.Data>() {
                    @Override
                    public void onResponse(@NotNull Response<ServiceQuery.Data> response) {
                        List<ServiceQuery.Service> allServiceTypes = response.data().service();
                        for (ServiceQuery.Service serv:
                                allServiceTypes) {
                            Service service = new Service();

                            service.setId(Long.parseLong(serv.id()));
                            service.setName(serv.name());
                            service.setImage(serv.image());
                            service.setDescription(serv.description());
                            service.setPrice(serv.price());
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
