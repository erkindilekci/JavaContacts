package com.erkindilekci.detailedcontactsapp.data.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.erkindilekci.detailedcontactsapp.data.model.Contact;

@Database(entities = {Contact.class}, version = 1, exportSchema = false)
public abstract class ContactDatabase extends RoomDatabase {

    public abstract ContactDao contactDao();
}
