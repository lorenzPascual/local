package com.example.juan_ted_ui.tasker.requests;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.juan_ted_ui.R;
import com.example.juan_ted_ui.customer.home.availabletaskers.Tasker;
import com.example.juan_ted_ui.customer.services.ServicesAdapter;
import com.example.juan_ted_ui.customer.services.ServicesFragment;
import com.example.juan_ted_ui.customer.services.ServicesViewModel;
import com.example.juan_ted_ui.model.Transaction;

import java.util.ArrayList;

public class RequestListFragment extends Fragment {


    RequestsAdapter recyclerViewAdapter;
    RecyclerView recyclerView;
    ArrayList<Transaction> requests;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_tasker_requests_request_list, container, false);
        recyclerView = (RecyclerView) root.findViewById(R.id.rvRequests);
        requests = new ArrayList<Transaction>();
        Transaction transaction = new Transaction();
        transaction.setCustomer("name");
        requests.add(transaction);

        recyclerViewAdapter = new RequestsAdapter(requests);
        recyclerView.setAdapter(recyclerViewAdapter);
        GridLayoutManager gridLayoutManager =
                new GridLayoutManager(getContext(),1,GridLayoutManager.VERTICAL,false);

        recyclerView.setLayoutManager(gridLayoutManager);

        return root;
    }
}