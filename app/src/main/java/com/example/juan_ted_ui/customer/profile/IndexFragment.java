package com.example.juan_ted_ui.customer.profile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.juan_ted_ui.R;
import com.example.juan_ted_ui.customer.servicetypes.ServiceTypesFragmentDirections;

public class IndexFragment extends Fragment {

    private View v;
    LinearLayout updateLocView,updateProfile;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_customer_profile_index, container, false);

        updateLocView = (LinearLayout) v.findViewById(R.id.btnupdateloc);
        updateLocView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action =
                        IndexFragmentDirections
                                .navigateProfileToGeolocation(1);
                Navigation.findNavController(view).navigate(action);
            }
        });
        updateProfile = (LinearLayout) v.findViewById(R.id.btnupdateprofile);
        updateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action =
                        IndexFragmentDirections
                                .navigateProfileToUpdateprofile(1);
                Navigation.findNavController(view).navigate(action);
            }
        });
        return v;
    }
}