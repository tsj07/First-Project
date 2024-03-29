package com.example.activitysample.Fragments;

import static android.Manifest.permission.READ_CONTACTS;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.activitysample.ContactsAdaptor;
import com.example.activitysample.R;
import com.example.activitysample.RoomDatabase.ContactsData;
import com.example.activitysample.RoomDatabase.ContactsViewModel;
import com.example.activitysample.RvInterface;
import com.example.activitysample.databinding.FirstFragmentBinding;

import java.util.ArrayList;
import java.util.List;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class FirstFragment extends Fragment implements RvInterface {

    Context context;
    ArrayList<ContactsData> listContactsData = new ArrayList<>();
    FirstFragmentBinding firstFragmentBinding;
    ContactsAdaptor adapter;
    ContactsViewModel contactsViewModel;

    public FirstFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        firstFragmentBinding = FirstFragmentBinding.inflate(inflater, container, false);
        View view = firstFragmentBinding.getRoot();
        assert container != null;
        context = container.getContext();

        contactsViewModel = new ViewModelProvider((ViewModelStoreOwner) FirstFragment.this).get(ContactsViewModel.class);

        adapter = new ContactsAdaptor(getActivity(), listContactsData, this);
        firstFragmentBinding.recyclerView.setLayoutManager(new LinearLayoutManager((getContext())));
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(firstFragmentBinding.recyclerView);


        contactsViewModel.getListLiveData().observe((LifecycleOwner) getActivity(), new Observer<List<ContactsData>>() {
            @Override
            public void onChanged(List<ContactsData> contactsData) {
                if (contactsData.size() > 0) {
                    for (int i = 0; i < contactsData.size(); i++) {
                        ContactsData model = new ContactsData(contactsData.get(i).getContactsName(), contactsData.get(i).getContactsPhone());
                        listContactsData.add(model);
                    }
                    firstFragmentBinding.recyclerView.setAdapter(adapter);

                } else {
                    checkPermission();
                    callPermission();
                }
            }
        });

        return view;

    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,
            ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @SuppressLint("NotifyDataSetChanged")
        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int pos = viewHolder.getAbsoluteAdapterPosition();
            TextView tvPhone = viewHolder.itemView.findViewById(R.id.tvContactPhone);
            String number = tvPhone.getText().toString();

            switch (direction) {
                case ItemTouchHelper.RIGHT:
                    itemOnClick(pos, number);
                    adapter.notifyDataSetChanged();
                    break;
                case ItemTouchHelper.LEFT:
                    sendMessage(number);
                    adapter.notifyDataSetChanged();
                    break;
            }
        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addSwipeRightBackgroundColor(ContextCompat.getColor(context, R.color.purple_200))
                    .addSwipeRightActionIcon(R.drawable.ic_person_2)
                    .addSwipeLeftBackgroundColor(ContextCompat.getColor(context, R.color.orange))
                    .addSwipeLeftActionIcon(R.drawable.ic_message)
                    .create()
                    .decorate();


            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

        }
    };

    private void checkPermission() {
        if (ContextCompat.checkSelfPermission
                (context, READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions
                    ((Activity) context, new String[]{READ_CONTACTS}, 100);

        } else {
            getContacts();
        }
    }

    private void callPermission() {
        if (ContextCompat.checkSelfPermission
                (context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions
                    ((Activity) context, new String[]{Manifest.permission.CALL_PHONE}, 100);
        }
    }

    private void getContacts() {
        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        String sort = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC";
        Cursor cursor = getActivity()
                .getContentResolver()
                .query(uri, null, null, null, sort);

        if (cursor.getCount() > 0) {

            while (cursor.moveToNext()) {

                @SuppressLint("Range") String id = cursor.getString(cursor.getColumnIndex(
                        ContactsContract.Contacts._ID
                ));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(
                        ContactsContract.Contacts.DISPLAY_NAME
                ));
                Uri uriPhone = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
                String selection = ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " =?";

                Cursor phoneCursor = getActivity().getContentResolver().query(
                        uriPhone, null, selection, new String[]{id}, null
                );

                if (phoneCursor.moveToNext()) {
                    @SuppressLint("Range") String number = phoneCursor.getString(phoneCursor.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.NUMBER
                    ));

                    ContactsData data = new ContactsData(name, number);
                    contactsViewModel.insert(data);

                    phoneCursor.close();
                }
            }
            cursor.close();
        }
    }

    @Override
    public void itemOnClick(int position, String phoneNo) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phoneNo));
        startActivity(intent);
    }

    @Override
    public void sendMessage(String phoneNo) {
        Intent smsIntent = new Intent(Intent.ACTION_VIEW);
        smsIntent.putExtra("address", phoneNo);
        smsIntent.setType("vnd.android-dir/mms-sms");
        startActivity(smsIntent);
    }

}


//    @Override
//    public void onAttach(@NonNull Context context) {
//        super.onAttach(context);
////        Toast.makeText(getContext(), "onAttach", Toast.LENGTH_SHORT).show();
//        Log.d("data", "onAttach", null);
//    }
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        Toast.makeText(getContext(), "onCreate", Toast.LENGTH_SHORT).show();
//        Log.d("data", "onCreate", null);
//    }
//
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
////        Toast.makeText(getContext(), "onViewCreated", Toast.LENGTH_SHORT).show();
//        Log.d("data", "onViewCreated", null);
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
////        Toast.makeText(getContext(), "onStart", Toast.LENGTH_SHORT).show();
//        Log.d("data", "onStart", null);
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
////        Toast.makeText(getContext(), "onResume", Toast.LENGTH_SHORT).show();
//        Log.d("data", "onResume", null);
//
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
////        Toast.makeText(getContext(), "onPause", Toast.LENGTH_SHORT).show();
//        Log.d("data", "onPause", null);
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
////        Toast.makeText(getContext(), "onStop", Toast.LENGTH_SHORT).show();
//        Log.d("data", "onStop", null);
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
////        Toast.makeText(getContext(), "onDestroyView", Toast.LENGTH_SHORT).show();
//        Log.d("data", "onDestroyView", null);
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
////        Toast.makeText(getContext(), "onDestroy", Toast.LENGTH_SHORT).show();
//        Log.d("data", "onDestroy", null);
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
////        Toast.makeText(getContext(), "onDetach", Toast.LENGTH_SHORT).show();
//        Log.d("data", "onDetach", null);
//    }
//
//}


