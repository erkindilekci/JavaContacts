package com.erkindilekci.detailedcontactsapp.presentation.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.erkindilekci.detailedcontactsapp.R;
import com.erkindilekci.detailedcontactsapp.databinding.FragmentDetailsBinding;
import com.erkindilekci.detailedcontactsapp.data.model.Contact;
import com.erkindilekci.detailedcontactsapp.presentation.viewmodel.DetailsViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DetailsFragment extends Fragment {

    private FragmentDetailsBinding binding;

    private Contact sentContact;

    private DetailsViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(this).get(DetailsViewModel.class);

        DetailsFragmentArgs bundle = DetailsFragmentArgs.fromBundle(getArguments());
        sentContact = bundle.getContact();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false);

        binding.setDetailsFragment(this);
        binding.setDetailsToolbarTitle(sentContact.getContactName());
        binding.setSentContact(sentContact);

        return binding.getRoot();
    }

    public void handleUpdateButtonClick(int contactId, String contactName, String contactNumber) {
        viewModel.updateContact(contactId, contactName, contactNumber);

        getParentFragmentManager().popBackStack();
    }
}