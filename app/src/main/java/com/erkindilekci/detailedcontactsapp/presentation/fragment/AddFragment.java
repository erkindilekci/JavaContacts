package com.erkindilekci.detailedcontactsapp.presentation.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.erkindilekci.detailedcontactsapp.R;
import com.erkindilekci.detailedcontactsapp.databinding.FragmentAddBinding;
import com.erkindilekci.detailedcontactsapp.presentation.viewmodel.AddViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AddFragment extends Fragment {

    private FragmentAddBinding binding;

    private AddViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(this).get(AddViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add, container, false);

        binding.setAddFragment(this);
        binding.setAddToolbarTitle("Add Contact");

        return binding.getRoot();
    }

    public void handleAddButtonClick(String contactName, String contactNumber) {
        viewModel.insertContact(contactName, contactNumber);

        getParentFragmentManager().popBackStack();
    }
}