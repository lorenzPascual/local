package com.example.juan_ted_ui.customer.servicetypes;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.juan_ted_ui.R;
import com.example.juan_ted_ui.customer.services.ServicesFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ServiceTypesAdapter extends
    RecyclerView.Adapter<ServiceTypesAdapter.ViewHolder> {
    private List<ServiceType> mServices;
    private FragmentActivity mActivity;

    // Pass in the contact array into the constructor
    public ServiceTypesAdapter(ArrayList<ServiceType> services, FragmentActivity activity) {
        mServices = services;
        mActivity = activity;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_customer_service_type, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ServiceType service = mServices.get(position);

        // Set item views based on your views and data model
        TextView textView = holder.nameTextView;
        textView.setText(service.getName());
        ImageView imageser = holder.imageService;
        Picasso.get().load(R.drawable.home_repair).into(imageser);
        holder.cardServiceType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action =
                        ServiceTypesFragmentDirections
                                .navigateToServices(service.getId());
                Navigation.findNavController(view).navigate(action);
                Log.d("res", String.valueOf(service.getId()));
            }
        });
//        Button button = holder.messageButton;
//        button.setText(service.isOnline() ? "Message" : "Offline");
//        button.setEnabled(service.isOnline());
    }

    @Override
    public int getItemCount() {
        return mServices.size();
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;
        public Button messageButton;
        public ImageView imageService;

        public CardView cardServiceType;
        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.service_name);
            imageService = (ImageView) itemView.findViewById(R.id.imgService);
            cardServiceType = (CardView) itemView.findViewById(R.id.servicecard);
//            messageButton = (Button) itemView.findViewById(R.id.message_button);
        }
    }
}