package com.example.juan_ted_ui.customer.home.availabletaskers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.juan_ted_ui.R;
import com.example.juan_ted_ui.customer.home.ChooseScheduleFragmentDirections;
import com.example.juan_ted_ui.customer.services.Service;
import com.example.juan_ted_ui.customer.services.ServicesAdapter;

import java.util.ArrayList;
import java.util.List;

public class AvailableTaskerAdapter extends
        RecyclerView.Adapter<AvailableTaskerAdapter.ViewHolder> {
    private List<Tasker> mTaskers;

    // Pass in the contact array into the constructor
    public AvailableTaskerAdapter(ArrayList<Tasker> taskers) {
        mTaskers = taskers;
    }
    @NonNull
    @Override
    public AvailableTaskerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_customer_taskerlist, parent, false);

        // Return a new holder instance
        AvailableTaskerAdapter.ViewHolder viewHolder = new AvailableTaskerAdapter.ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AvailableTaskerAdapter.ViewHolder holder, int position) {
        Tasker taskers = mTaskers.get(position);

        // Set item views based on your views and data model
        TextView textView = holder.nameTextView;
        textView.setText(taskers.getFirstname()+" "+taskers.getLastname());

        holder.taskercard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action =
                        AvailableTaskersFragmentDirections
                                .navigateToViewtasker();
                Navigation.findNavController(view).navigate(action);
            }
        });
//        Button button = holder.messageButton;
//        button.setText(service.isOnline() ? "Message" : "Offline");
//        button.setEnabled(service.isOnline());
    }

    @Override
    public int getItemCount() {
        return mTaskers.size();
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;
        public TextView priceTextView;
        public TextView imgTextView;
        public CardView taskercard;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            taskercard = (CardView) itemView.findViewById(R.id.taskercard);
            nameTextView = (TextView) itemView.findViewById(R.id.taskername);
//            imgTextView = (TextView) itemView.findViewById(R.id.service_description);
//            priceTextView = (TextView) itemView.findViewById(R.id.service_price);
        }
    }
}