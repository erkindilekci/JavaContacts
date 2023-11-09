package com.erkindilekci.detailedcontactsapp.data.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Contact implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "contact_id")
    private int contactId = 0;
    @NonNull
    @ColumnInfo(name = "contact_name")
    private String contactName;
    @NonNull
    @ColumnInfo(name = "contact_number")
    private String contactNumber;

    public Contact() {
    }

    public Contact(@NonNull String contactName, @NonNull String contactNumber) {
        this.contactName = contactName;
        this.contactNumber = contactNumber;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    @NonNull
    public String getContactName() {
        return contactName;
    }

    public void setContactName(@NonNull String contactName) {
        this.contactName = contactName;
    }

    @NonNull
    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(@NonNull String contactNumber) {
        this.contactNumber = contactNumber;
    }
}