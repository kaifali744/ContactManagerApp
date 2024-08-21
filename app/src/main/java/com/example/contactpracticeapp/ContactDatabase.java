package com.example.contactpracticeapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Contact.class},version = 1)
public abstract class ContactDatabase extends RoomDatabase {

    public abstract ContactDAO contactDAO();

    //Singelton pattern to create only one instance of room database
    private static ContactDatabase dbInstance;

    public static synchronized ContactDatabase getInstance(Context context){
        if (dbInstance == null){
            dbInstance = Room.databaseBuilder(context.getApplicationContext(),
                    ContactDatabase.class,
                    "contact_db").fallbackToDestructiveMigrationOnDowngrade().build();
        }
        return dbInstance;
    }
}
