package com.erkindilekci.detailedcontactsapp.data.repository;

import androidx.lifecycle.MutableLiveData;

import com.erkindilekci.detailedcontactsapp.data.model.Contact;
import com.erkindilekci.detailedcontactsapp.data.room.ContactDao;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ContactRepository {

    private MutableLiveData<List<Contact>> contactList;
    private ContactDao dao;

    @Inject
    public ContactRepository(ContactDao dao) {
        contactList = new MutableLiveData<>();
        this.dao = dao;
    }

    public MutableLiveData<List<Contact>> getContactList() {
        return contactList;
    }

    public void insertContact(String contactName, String contactNumber) {
        dao.insertContact(new Contact(contactName, contactNumber))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public void updateContact(int contactId, String contactName, String contactNumber) {
        dao.updateContact(contactId, contactName, contactNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public void searchContacts(String searchedWord) {
        dao.getSearchedContacts(searchedWord)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(searchedContacts -> contactList.setValue(searchedContacts));
    }

    public void deleteContact(int contactId) {
        dao.deleteContact(contactId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public void getAllContacts() {
        dao.getAllPeople()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(contacts -> contactList.setValue(contacts));
    }
}
