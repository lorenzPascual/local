package com.example.juan_ted_ui.customer.services;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.juan_ted_ui.R;
import com.example.juan_ted_ui.customer.servicetypes.ServiceTypesFragmentDirections;

import java.util.ArrayList;

public class ServicesFragment extends Fragment implements LifecycleOwner {

    private ServicesViewModel servicesViewModel;
    ServicesAdapter recyclerViewAdapter;
    RecyclerView recyclerView;
    ServicesFragment context;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_customer_services, container, false);
        context = this;
        recyclerView = root.findViewById(R.id.rvServices);

        long ID = ServicesFragmentArgs.fromBundle(getArguments()).getId();

        Log.d("ress", String.valueOf(ID));
        servicesViewModel =
                ViewModelProviders.of(this).get(ServicesViewModel.class);
        servicesViewModel.setID(ID);
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
        Button btngotoschedpicker = (Button) root.findViewById(R.id.btngotoschedpicker);
        btngotoschedpicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action =
                        ServicesFragmentDirections
                                .navigateToLocation(1);
                Navigation.findNavController(view).navigate(action);
            }
        });
        return root;
    }
    Observer<ArrayList<Service>> serviceListUpdateObserver = new Observer<ArrayList<Service>>() {
        @Override
        public void onChanged(ArrayList<Service> userArrayList) {
            recyclerViewAdapter = new ServicesAdapter(userArrayList);
            recyclerView.setAdapter(recyclerViewAdapter);
            GridLayoutManager gridLayoutManager =
                    new GridLayoutManager(getContext(),1,GridLayoutManager.VERTICAL,false);

            recyclerView.setLayoutManager(gridLayoutManager);
        }
    };
}
