package com.erkindilekci.detailedcontactsapp.presentation.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuProvider;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.erkindilekci.detailedcontactsapp.R;
import com.erkindilekci.detailedcontactsapp.databinding.FragmentHomeBinding;
import com.erkindilekci.detailedcontactsapp.presentation.adapter.ContactAdapter;
import com.erkindilekci.detailedcontactsapp.presentation.viewmodel.HomeViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeFragment extends Fragment implements SearchView.OnQueryTextListener {

    private FragmentHomeBinding binding;

    private HomeViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);

        ((AppCompatActivity) getActivity()).setSupportActionBar(binding.homeToolbar);

        requireActivity().addMenuProvider(new MenuProvider() {
            @Override
            public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
                menuInflater.inflate(R.menu.toolbar_menu, menu);

                MenuItem item = menu.findItem(R.id.action_search);
                SearchView searchView = (SearchView) item.getActionView();
                searchView.setOnQueryTextListener(HomeFragment.this);
            }

            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
                return false;
            }
        }, getViewLifecycleOwner(), Lifecycle.State.RESUMED);

        viewModel.contactList.observe(getViewLifecycleOwner(), contacts -> {
            binding.setContactAdapter(new ContactAdapter(getContext(), contacts, viewModel));
        });

        binding.setHomeFragment(this);
        binding.setHomeToolbarTitle("Contacts");

        return binding.getRoot();
    }

    public void handleFabButtonClick(View view) {
        Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_addFragment);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        viewModel.searchContacts(query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        viewModel.searchContacts(newText);
        return true;
    }
}