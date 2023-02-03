package com.example.activitysample;

import androidx.lifecycle.LiveData;

import com.example.activitysample.RoomDatabase.ContactsDAO;
import com.example.activitysample.RoomDatabase.ContactsData;

import java.util.List;

public class ContactsRepository {
    private ContactsDAO contactsDAO;
    private LiveData<List<ContactsData>>
}
