package com.example.juan_ted_ui.customer.servicetypes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.juan_ted_ui.R;

import java.util.ArrayList;

public class ServiceTypesFragment extends Fragment implements LifecycleOwner {

    private ServiceTypesViewModel servicesViewModel;
    ServiceTypesAdapter recyclerViewAdapter;
    RecyclerView recyclerView;
    ServiceTypesFragment context;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_customer_services, container, false);
        context = this;
        recyclerView = root.findViewById(R.id.rvServices);
        servicesViewModel =
                ViewModelProviders.of(this).get(ServiceTypesViewModel.class);
        servicesViewModel.getUserMutableLiveData().observe(context, serviceListUpdateObserver);


//        final TextView textView = root.findViewById(R.id.text_home);
//        servicesViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//            public void onChanged(@Nullable String s) {
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }
    Observer<ArrayList<ServiceType>> serviceListUpdateObserver = new Observer<ArrayList<ServiceType>>() {
        @Override
        public void onChanged(ArrayList<ServiceType> userArrayList) {
            recyclerViewAdapter = new ServiceTypesAdapter(userArrayList,getActivity());
            recyclerView.setAdapter(recyclerViewAdapter);
            GridLayoutManager gridLayoutManager =
                    new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);

            recyclerView.setLayoutManager(gridLayoutManager);
        }
    };
}
