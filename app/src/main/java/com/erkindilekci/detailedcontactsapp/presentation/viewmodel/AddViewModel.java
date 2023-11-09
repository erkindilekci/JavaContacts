package com.erkindilekci.detailedcontactsapp.presentation.viewmodel;

import androidx.lifecycle.ViewModel;

import com.erkindilekci.detailedcontactsapp.data.repository.ContactRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class AddViewModel extends ViewModel {

    private ContactRepository repository;

    @Inject
    public AddViewModel(ContactRepository repository) {
        this.repository = repository;
    }

    public void insertContact(String contactName, String contactNumber) {
        repository.insertContact(contactName, contactNumber);
    }
}
