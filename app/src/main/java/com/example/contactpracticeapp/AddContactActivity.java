package com.example.contactpracticeapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.contactpracticeapp.databinding.ActivityAddContactBinding;

public class AddContactActivity extends AppCompatActivity {
    ActivityAddContactBinding addContactBinding;
    AddContactClickHandler contactClickHandler;
    Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_contact);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        MyViewModel myViewModel = new ViewModelProvider(this).get(MyViewModel.class);
        contact = new Contact();
        addContactBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_add_contact);
        contactClickHandler = new AddContactClickHandler(this,contact,myViewModel);
        addContactBinding.setContact(contact);
        addContactBinding.setClickHandler(contactClickHandler);
    }
}