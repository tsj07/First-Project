package com.example.activitysample;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Ignore;

import com.example.activitysample.RoomDatabase.ContactsData;

import java.util.ArrayList;
import java.util.List;

public class ContactsAdaptor extends RecyclerView.Adapter<ContactsAdaptor.ContactsHolder> {

    Activity activity;
    ArrayList<ContactsModel> arrayList;
    RvInterface rvInterface;
    List<ContactsData> contactsEntityList;

    public ContactsAdaptor(Activity activity, ArrayList<ContactsModel> arrayList, RvInterface rvInterface) {
        this.activity = activity;
        this.arrayList = arrayList;
        this.rvInterface = rvInterface;
    }

    @Ignore
    public ContactsAdaptor(FragmentActivity activity, List<ContactsData> contactsEntityList) {
        this.activity = activity;
        this.contactsEntityList = contactsEntityList;
    }

    public ContactsAdaptor(Activity activity, ArrayList<ContactsModel> arrayList) {
        this.activity = activity;
        this.arrayList = arrayList;
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
        ImageButton btnCall;

        public ContactsHolder(@NonNull View itemView, RvInterface rvInterface) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvContactName);
            tvPhone = itemView.findViewById(R.id.tvContactPhone);
            btnCall = itemView.findViewById(R.id.btnCall);

            btnCall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Clicked", Toast.LENGTH_SHORT).show();
                }
            });

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
