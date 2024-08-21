package com.example.contactpracticeapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactpracticeapp.databinding.ContactListItemBinding;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ContactViewHolder> {

    private ArrayList<Contact> contactArrayList;

    public Adapter(ArrayList<Contact> contactArrayList) {
        this.contactArrayList = contactArrayList;
    }

    public void updateList(ArrayList<Contact> filteredList){
        contactArrayList =filteredList;
        notifyDataSetChanged();
    }

    public void setContactArrayList(ArrayList<Contact> contactArrayList) {
        this.contactArrayList = contactArrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ContactListItemBinding contactListItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.contact_list_item,
                parent,
                false);
        return new ContactViewHolder(contactListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contact currentContact = contactArrayList.get(position);
        holder.contactListItemBinding.setContact(currentContact);
    }

    @Override
    public int getItemCount() {
        if (contactArrayList != null){
            return contactArrayList.size();
        }
        return 0;
    }

    class ContactViewHolder extends RecyclerView.ViewHolder{
        ContactListItemBinding contactListItemBinding;

        public ContactViewHolder(@NonNull ContactListItemBinding contactListItemBinding) {
            super(contactListItemBinding.getRoot());
            this.contactListItemBinding = contactListItemBinding;
        }
    }
}
