package com.erkindilekci.detailedcontactsapp.presentation.viewmodel;

import androidx.lifecycle.ViewModel;

import com.erkindilekci.detailedcontactsapp.data.repository.ContactRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class DetailsViewModel extends ViewModel {

    private ContactRepository repository;

    @Inject
    public DetailsViewModel(ContactRepository repository) {
        this.repository = repository;
    }


    public void updateContact(int contactId, String contactName, String contactNumber) {
        repository.updateContact(contactId, contactName, contactNumber);
    }
}
