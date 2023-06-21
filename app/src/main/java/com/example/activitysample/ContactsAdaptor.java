package com.example.activitysample;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.activitysample.RoomDatabase.ContactsData;

import java.util.ArrayList;

public class ContactsAdaptor extends RecyclerView.Adapter<ContactsAdaptor.ContactsHolder> {
    Activity activity;
    ArrayList<ContactsData> listData;
    RvInterface rvInterface;

    public ContactsAdaptor(Activity activity, ArrayList<ContactsData> listData, RvInterface rvInterface) {
        this.activity = activity;
        this.listData = listData;
        this.rvInterface = rvInterface;
    }

    @NonNull
    @Override
    public ContactsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.contacts_view, parent, false);
        return new ContactsHolder(view, rvInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsHolder holder, int position) {
        ContactsData contactsData = listData.get(position);
        holder.tvName.setText(contactsData.getContactsName());
        holder.tvPhone.setText(contactsData.getContactsPhone());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public static class ContactsHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvPhone;
        ImageButton btnCall;

        public ContactsHolder(@NonNull View itemView, RvInterface rvInterface) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvContactName);
            tvPhone = itemView.findViewById(R.id.tvContactPhone);
            btnCall = itemView.findViewById(R.id.btnCall);

            itemView.setOnClickListener(v -> {
                if (rvInterface != null) {
                    int pos = getAdapterPosition();
                    String number = tvPhone.getText().toString();

                    if (pos != RecyclerView.NO_POSITION) {
                        rvInterface.itemOnClick(pos, number);
                    }
                }
            });
        }
    }

}
