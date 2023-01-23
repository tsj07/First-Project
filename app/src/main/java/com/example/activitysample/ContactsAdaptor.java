package com.example.activitysample;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactsAdaptor extends RecyclerView.Adapter<ContactsAdaptor.ContactsHolder> {

    Activity activity;
    ArrayList<ContactsModel> arrayList;

    public ContactsAdaptor(Activity activity, ArrayList<ContactsModel> arrayList) {
        this.activity = activity;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ContactsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.contacts_view, parent, false);
        return new ContactsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsHolder holder, int position) {
         ContactsModel contactsModel = arrayList.get(position);
         holder.tvName.setText(contactsModel.getContactName());
         holder.tvPhone.setText(contactsModel.getPhone());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ContactsHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvPhone;

        public ContactsHolder(@NonNull View itemView) {
            super(itemView);
             tvName = itemView.findViewById(R.id.tvContactName);
             tvPhone = itemView.findViewById(R.id.tvContactPhone);

        }
    }

}
