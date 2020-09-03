package com.example.juan_ted_ui.tasker.requests;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.juan_ted_ui.R;
import com.example.juan_ted_ui.model.Transaction;

import java.util.ArrayList;
import java.util.List;

public class RequestsAdapter extends
    RecyclerView.Adapter<RequestsAdapter.ViewHolder> {
    private List<Transaction> mRequests;

    // Pass in the contact array into the constructor
    public RequestsAdapter(ArrayList<Transaction> requests) {
        mRequests = requests;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_tasker_request, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Transaction service = mRequests.get(position);

        // Set item views based on your views and data model
        TextView textView = holder.nameTextView;
        textView.setText(service.getCustomer());
    }

    @Override
    public int getItemCount() {
        return mRequests.size();
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.customername);
        }
    }
}