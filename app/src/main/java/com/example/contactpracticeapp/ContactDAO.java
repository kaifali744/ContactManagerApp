package com.example.contactpracticeapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ContactDAO {
    @Insert
    void add(Contact contact);

    @Delete
    void deleteContact(Contact contact);

    @Query("SELECT * FROM contacts_table")
    LiveData<List<Contact>> getAllContact();


}
