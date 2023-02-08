package com.example.activitysample.RoomDatabase;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ContactsViewModel extends AndroidViewModel {
    private ContactsRepository mRepository;
    private LiveData<List<ContactsData>> listLiveData;

    public ContactsViewModel(@NonNull Application application) {
        super(application);
        mRepository = new ContactsRepository(application);
        listLiveData = mRepository.getListLiveData();
    }

    public LiveData<List<ContactsData>> getListLiveData() {
        return listLiveData;
    }

    public void insert(ContactsData contactsEntity) {
        mRepository.addContacts(contactsEntity);
    }

    public void getContactsById(int id) {
        mRepository.getContactsById(id);
    }

}
