package com.example.activitysample;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FirstFragment extends Fragment {

    Context context;
    ArrayList<ContactsModel> arrayList = new ArrayList<>();

    public FirstFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.first_fragment, container, false);
        assert container != null;
        context = container.getContext();

        checkPermission();

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        ContactsAdaptor adapter = new ContactsAdaptor(getActivity(), arrayList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

     private void checkPermission(){
        if (ContextCompat.checkSelfPermission
                (context, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions
                    ((Activity) context, new String[]{Manifest.permission.READ_CONTACTS}, 100);

        } else {
            getContacts();
        }
    }

    private void getContacts() {

        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        String sort = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC";
        Cursor cursor = requireActivity()
                .getContentResolver()
                .query(uri, null, null, null, sort);

        if ( cursor.getCount() > 0 ) {

            while (cursor.moveToNext()){

                @SuppressLint("Range") String id = cursor.getString(cursor.getColumnIndex(
                        ContactsContract.Contacts._ID
                ));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(
                        ContactsContract.Contacts.DISPLAY_NAME
                ));
                Uri uriPhone = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
                String selection = ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " =?";

                Cursor phoneCursor = requireActivity().getContentResolver().query(
                        uriPhone, null, selection, new String[]{id}, null
                );

                if (phoneCursor.moveToNext()){
                    @SuppressLint("Range") String number = phoneCursor.getString(phoneCursor.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.NUMBER
                    ));
                    ContactsModel model = new ContactsModel(name, number);
                    arrayList.add(model);
                    model.setContactName(name);
                    model.setPhone(number);
                    phoneCursor.close();
                }
            }
            cursor.close();
        }
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


