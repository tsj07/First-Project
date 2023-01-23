package com.example.activitysample;

public class ContactsModel {
    String ContactName;
    String Phone;

    public ContactsModel(String name, String phone) {
        this.ContactName = name;
        this.Phone = phone;
    }

    public String getContactName() {
        return ContactName;
    }

    public void setContactName(String name) {
        ContactName = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
}
