package com.example.juan_ted_ui.customer.home.availabletaskers;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.juan_ted_ui.R;
import com.example.juan_ted_ui.customer.services.ServicesAdapter;
import com.example.juan_ted_ui.customer.services.ServicesFragment;
import com.example.juan_ted_ui.customer.services.ServicesFragmentArgs;
import com.example.juan_ted_ui.customer.services.ServicesFragmentDirections;
import com.example.juan_ted_ui.customer.services.ServicesViewModel;

import java.util.ArrayList;

public class AvailableTaskersFragment extends Fragment {
    private ServicesViewModel servicesViewModel;
    AvailableTaskerAdapter recyclerViewAdapter;
    RecyclerView recyclerView;
    ServicesFragment context;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_customer_home_availabletaskers, container, false);

        Log.d("asd","custom");
//

        recyclerView = root.findViewById(R.id.rvServices);

        ArrayList<Tasker> taskerlist = new ArrayList<Tasker>();
        Tasker tasker = new Tasker();
        tasker.setFirstname("Renz");
        tasker.setLastname("Pascual");
        taskerlist.add(tasker);

        recyclerViewAdapter = new AvailableTaskerAdapter(taskerlist);
        recyclerView.setAdapter(recyclerViewAdapter);
        GridLayoutManager gridLayoutManager =
                new GridLayoutManager(getContext(),1,GridLayoutManager.VERTICAL,false);

        recyclerView.setLayoutManager(gridLayoutManager);
//
//        Button btngotoschedpicker = (Button) root.findViewById(R.id.btngotoschedpicker);
//        btngotoschedpicker.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NavDirections action =
//                        ServicesFragmentDirections
//                                .navigateToLocation(1);
//                Navigation.findNavController(view).navigate(action);
//            }
//        });
        return root;
    }
}
