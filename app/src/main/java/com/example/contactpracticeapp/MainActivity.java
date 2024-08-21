package com.example.contactpracticeapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactpracticeapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //DataSource
    private ContactDatabase contactDatabase;
    private ArrayList<Contact> contactArrayList = new ArrayList<>();

    //Adapter
    private Adapter adapter;

    //Binding
    private ActivityMainBinding mainBinding;
    private MyClickHandler clickHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //DataBinding
        mainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        clickHandler = new MyClickHandler(this);
        mainBinding.setClickHandler(clickHandler);

        //RecyclerView
        RecyclerView recyclerView = mainBinding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        //Database
        contactDatabase = ContactDatabase.getInstance(this);

        //ViewModel
        MyViewModel viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        //Loading Data from RoomDataBase
        viewModel.getAllContacts().observe(this, new Observer<List<Contact>>() {
            @Override
            public void onChanged(List<Contact> contacts) {

                for (Contact c: contacts) {
                    Log.v("Tagy",c.getName());
                    contactArrayList.add(c);
                }
                adapter.notifyDataSetChanged();
            }
        });

        //Adapter
        adapter = new Adapter(contactArrayList);
        //Linking recyclerView with Adapter
        recyclerView.setAdapter(adapter);

        //Swipe left to delete contact Functionlity
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Contact c =contactArrayList.get(viewHolder.getAdapterPosition());
                viewModel.deleteContact(c);
            }
        }).attachToRecyclerView(recyclerView);

        //Set up the SearchView
        SearchView searchView = mainBinding.searchView;
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterContact(newText);
                return false;
            }
        });


    }
    private void filterContact(String query){
        ArrayList<Contact> filtered_list = new ArrayList<>();
        for (Contact c: contactArrayList) {
            if (c.getName().toLowerCase().contains(query.toLowerCase())||
            c.getEmail().toLowerCase().contains(query.toLowerCase())){
                filtered_list.add(c);
            }
        }
        adapter.updateList(filtered_list);
    }
}