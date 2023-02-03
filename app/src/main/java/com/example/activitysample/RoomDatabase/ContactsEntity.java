package com.example.activitysample.RoomDatabase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "My_contacts")
public class ContactsEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo (name = "id")
    private int id;

    @ColumnInfo(name = "Name")
    private String contactsName;

    @ColumnInfo (name = "PhoneNo")
     private String contactsPhone;

    public ContactsEntity(int id, String contactsName, String contactsPhone) {
        this.id = id;
        this.contactsName = contactsName;
        this.contactsPhone = contactsPhone;
    }

    @Ignore
    public ContactsEntity(String contactsName, String contactsPhone) {
        this.contactsName = contactsName;
        this.contactsPhone = contactsPhone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContactsName() {
        return contactsName;
    }

    public void setContactsName(String contactsName) {
        this.contactsName = contactsName;
    }

    public String getContactsPhone() {
        return contactsPhone;
    }

    public void setContactsPhone(String contactsPhone) {
        this.contactsPhone = contactsPhone;
    }
}
