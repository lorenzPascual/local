package com.example.juan_ted_ui.customer.viewtasker;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.juan_ted_ui.R;
import com.example.juan_ted_ui.ServiceQuery;
import com.example.juan_ted_ui.TaskerQuery;
import com.example.juan_ted_ui.customer.services.Service;
import com.example.juan_ted_ui.customer.servicetypes.ServiceType;
import com.example.juan_ted_ui.customer.servicetypes.ServiceTypesAdapter;
import com.example.juan_ted_ui.customer.servicetypes.ServiceTypesFragment;
import com.example.juan_ted_ui.customer.servicetypes.ServiceTypesViewModel;
import com.example.juan_ted_ui.util.ApolloConnector;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ViewTaskerFragment extends Fragment {

    private ViewTaskerViewModel mViewModel;
    ReviewAdapter reviewAdapter;

    ViewTaskerFragment context;
    RecyclerView recyclerView;
    ViewTaskerViewModel viewTaskerViewModel;

    TextView taskername;

    private int taskerID;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_customer_viewtasker, container, false);
        recyclerView = root.findViewById(R.id.rvReviews);


        taskername = root.findViewById(R.id.tasker_name);

        taskerID = 1;
        ApolloConnector.setupApollo().query(
                TaskerQuery
                        .builder().tasker_id(taskerID)
                        .build())
                .enqueue(new ApolloCall.Callback<TaskerQuery.Data>() {
                    @Override
                    public void onResponse(@NotNull Response<TaskerQuery.Data> response) {
                        List<TaskerQuery.Tasker> Tasker = response.data().tasker();
                        for (TaskerQuery.Tasker tasker:
                                Tasker) {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    taskername.setText(tasker.firstName()+ " "+tasker.lastName());
                                }
                            });

                        }

                    }

                    @Override
                    public void onFailure(@NotNull ApolloException e) {
                        Log.d("res",e.toString());
                        Log.d("res",e.getMessage().toString());
                    }
                });

        return root;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
    }


}