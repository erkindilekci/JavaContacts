package com.erkindilekci.detailedcontactsapp.presentation.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.erkindilekci.detailedcontactsapp.data.model.Contact;
import com.erkindilekci.detailedcontactsapp.data.repository.ContactRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class HomeViewModel extends ViewModel {

    public MutableLiveData<List<Contact>> contactList = new MutableLiveData<>();

    private ContactRepository repository;

    @Inject
    public HomeViewModel(ContactRepository repository) {
        this.repository = repository;
        loadAllContacts();
        contactList = repository.getContactList();
    }

    public void searchContacts(String searchedWord) {
        repository.searchContacts(searchedWord);
    }

    public void deleteContact(int contactId) {
        repository.deleteContact(contactId);
    }

    public void loadAllContacts() {
        repository.getAllContacts();
    }
}
