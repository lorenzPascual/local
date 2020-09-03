package com.example.juan_ted_ui.customer.services;

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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ServicesAdapter extends
    RecyclerView.Adapter<ServicesAdapter.ViewHolder> {
    private List<Service> mServices;

    // Pass in the contact array into the constructor
    public ServicesAdapter(ArrayList<Service> services) {
        mServices = services;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_customer_service, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Service service = mServices.get(position);

        // Set item views based on your views and data model
        TextView textView = holder.nameTextView;
        textView.setText(service.getName());
//        ImageView imageser = holder.imageService;
//        Picasso.get().load(R.drawable.home_repair).into(imageser);
        holder.descTextView.setText(service.getDescription());
        holder.priceTextView.setText(service.getPrice().toString());
        holder.plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int new_quan = Integer.valueOf(holder.quantityEdit.getText().toString()) + 1;
                holder.quantityEdit.setText(String.valueOf(new_quan));
            }
        });
        holder.minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Integer.valueOf(holder.quantityEdit.getText().toString())>0) {
                    int new_quan = Integer.valueOf(holder.quantityEdit.getText().toString()) - 1;
                    holder.quantityEdit.setText(String.valueOf(new_quan));
                }
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
        public TextView priceTextView;
        public TextView descTextView;
        public Button minusButton;
        public Button plusButton;
        public EditText quantityEdit;
        public ImageView imageService;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.service_name);
            descTextView = (TextView) itemView.findViewById(R.id.service_description);
            priceTextView = (TextView) itemView.findViewById(R.id.service_price);
            imageService = (ImageView) itemView.findViewById(R.id.imgService);
            minusButton = (Button) itemView.findViewById(R.id.btnminus);
            plusButton = (Button) itemView.findViewById(R.id.btnadd);
            quantityEdit = (EditText) itemView.findViewById(R.id.edit_quan);
        }
    }
}