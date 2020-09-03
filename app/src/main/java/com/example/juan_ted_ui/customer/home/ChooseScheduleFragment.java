package com.example.juan_ted_ui.customer.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.juan_ted_ui.R;
import com.example.juan_ted_ui.customer.services.ServicesFragmentDirections;


public class ChooseScheduleFragment extends Fragment {




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_customer_home_choose_schedule, container, false);
        Button btnschedulepicked = (Button) v.findViewById(R.id.btnschedulepicked);
        btnschedulepicked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action =
                        ChooseScheduleFragmentDirections
                                .navigateToAvailabletasker();
                Navigation.findNavController(view).navigate(action);
            }
        });
        return v;
    }
}