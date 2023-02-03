package com.example.activitysample.RoomDatabase;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ContactsDAO {

    @Query("SELECT * FROM My_contacts")
    List<ContactsData> getAllContacts();

    @Query("SELECT * FROM My_contacts where id = id")
    ContactsData getItemByID(int id);

    @Insert
    void addContacts(ContactsData contactsData);

}
