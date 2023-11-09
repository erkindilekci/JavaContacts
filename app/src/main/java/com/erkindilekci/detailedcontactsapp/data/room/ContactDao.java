package com.erkindilekci.detailedcontactsapp.data.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.erkindilekci.detailedcontactsapp.data.model.Contact;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface ContactDao {

    @Query("SELECT * FROM contact ORDER BY contact_name ASC")
    Flowable<List<Contact>> getAllPeople();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable insertContact(Contact Contact);

    @Query("UPDATE contact SET contact_name=:contactName, contact_number=:contactNumber WHERE contact_id=:contactId")
    Completable updateContact(int contactId, String contactName, String contactNumber);

    @Query("DELETE FROM contact WHERE contact_id=:contactId")
    Completable deleteContact(int contactId);

    @Query("SELECT * FROM contact WHERE contact_name LIKE '%' || :searchedWord || '%'")
    Flowable<List<Contact>> getSearchedContacts(String searchedWord);
}
