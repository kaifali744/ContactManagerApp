package com.example.contactpracticeapp;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyRepository {
    private final ContactDAO contactDAO;
    ExecutorService executor;
    Handler handler;

    public MyRepository(Application application) {
        ContactDatabase contactDatabase = ContactDatabase.getInstance(application);
        this.contactDAO = contactDatabase.contactDAO();
        executor = Executors.newSingleThreadExecutor();
        handler = new Handler(Looper.getMainLooper());
    }

    public void addContact(Contact contact){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                contactDAO.add(contact);
            }
        });
    }

    public void deleteContact(Contact contact){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                contactDAO.deleteContact(contact);
            }
        });
    }

    public LiveData<List<Contact>> allContact(){
        return contactDAO.getAllContact();
    }
}
