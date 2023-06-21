package com.example.activitysample.RoomDatabase;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ContactsRepository {

    private LiveData<List<ContactsData>> listLiveData;
    private ContactsDAO mDao;

    ContactsRepository(Application application) {
        ContactsDatabase db = ContactsDatabase.getDatabase(application);
        mDao = db.contactsDAO(application);
        listLiveData = mDao.getAllContacts();
    }

    LiveData<List<ContactsData>> getListLiveData() {
        return listLiveData;
    }

    void addContacts(ContactsData contactsData) {
        ContactsDatabase.databaseExecutor.execute(() -> {
            mDao.addContacts(contactsData);
        });
    }

    void getContactsById(int id) {
        ContactsDatabase.databaseExecutor.execute(() -> {
            mDao.getContactsById(id);
        });
    }
}
