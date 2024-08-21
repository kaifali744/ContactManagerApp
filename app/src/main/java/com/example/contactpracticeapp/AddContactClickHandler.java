package com.example.contactpracticeapp;

import android.content.Context;
import android.content.Intent;
import android.view.View;

public class AddContactClickHandler {
    Context context;
    Contact contact;
    MyViewModel myViewModel;

    public AddContactClickHandler(Context context, Contact contact, MyViewModel myViewModel) {
        this.context = context;
        this.contact = contact;
        this.myViewModel = myViewModel;
    }

    public void onSaveBtnClicked(View view){
        Intent i = new Intent(context,MainActivity.class);
        Contact c = new Contact(contact.getName(), contact.getEmail());
        myViewModel.addNewContact(c);
        context.startActivity(i);
    }
}
