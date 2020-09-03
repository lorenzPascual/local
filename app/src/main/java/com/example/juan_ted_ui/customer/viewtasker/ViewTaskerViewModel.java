package com.example.juan_ted_ui.customer.viewtasker;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import java.util.ArrayList;

public class ViewTaskerViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    MutableLiveData<ArrayList<Review>> serviceLiveData;
    ArrayList<Review> serviceArrayList;

    public ViewTaskerViewModel() {
        serviceLiveData = new MutableLiveData<>();
        init();
    }
    public void init(){
        populateList();
        serviceLiveData.setValue(serviceArrayList);
    }

    public MutableLiveData<ArrayList<Review>> getUserMutableLiveData() {
        return serviceLiveData;
    }


    public void populateList(){

        Review service = new Review();
        service.setName("Barber");
        service.setDescription("Best rating movie");

        serviceArrayList = new ArrayList<>();
        serviceArrayList.add(service);
        serviceArrayList.add(service);
    }
}