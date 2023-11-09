package com.erkindilekci.detailedcontactsapp.presentation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.erkindilekci.detailedcontactsapp.R;
import com.erkindilekci.detailedcontactsapp.databinding.RecyclerRowBinding;
import com.erkindilekci.detailedcontactsapp.data.model.Contact;
import com.erkindilekci.detailedcontactsapp.presentation.fragment.HomeFragmentDirections;
import com.erkindilekci.detailedcontactsapp.presentation.viewmodel.HomeViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private Context mContext;
    private List<Contact> contacts;
    private HomeViewModel viewModel;

    public ContactAdapter(Context mContext, List<Contact> contacts, HomeViewModel viewModel) {
        this.mContext = mContext;
        this.contacts = contacts;
        this.viewModel = viewModel;
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {
        RecyclerRowBinding binding;

        public ContactViewHolder(RecyclerRowBinding b) {
            super(b.getRoot());
            binding = b;
        }
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ContactViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.recycler_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contact current = contacts.get(position);

        holder.binding.setCurrentContact(current);

        holder.binding.deleteButton.setOnClickListener(v -> {
            Snackbar.make(v, "Are you sure you want to delete " + current.getContactName(), Snackbar.LENGTH_INDEFINITE)
                    .setAction("Yes", v1 -> {viewModel.deleteContact(current.getContactId());})
                    .show();
        });
        holder.binding.recyclerViewCardView.setOnClickListener(v -> {
            HomeFragmentDirections.ActionHomeFragmentToDetailsFragment toDetailsFragment
                    = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(current);
            Navigation.findNavController(v).navigate(toDetailsFragment);
        });
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }
}
