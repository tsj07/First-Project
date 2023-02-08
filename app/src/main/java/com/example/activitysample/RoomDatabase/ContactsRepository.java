package com.example.activitysample.RoomDatabase;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ContactsRepository {

    private final LiveData<List<ContactsData>> listLiveData;
    private ContactsDAO mDao;
    Context context;

    ContactsRepository(Application application) {
        ContactsDatabase db = ContactsDatabase.getDatabase(application);
        listLiveData = mDao.getAllContacts();
        mDao = db.contactsDAO(context);
    }

    LiveData<List<ContactsData>> getListLiveData() {
        return listLiveData;
    }

    void addContacts(ContactsData contactsEntity) {
        ContactsDatabase.databaseExecutor.execute(() -> {
            mDao.addContacts(contactsEntity);
        });
    }

    void getContactsById(int id) {
        ContactsDatabase.databaseExecutor.execute(() -> {
            mDao.getContactsById(id);
        });
    }
}
