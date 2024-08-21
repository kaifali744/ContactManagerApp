package com.example.contactpracticeapp;

import android.content.Context;
import android.content.Intent;
import android.view.View;

public class MyClickHandler {
    Context context;

    public MyClickHandler(Context context) {
        this.context = context;
    }

    public void onFABclicked(View view){
        Intent i = new Intent(view.getContext(),AddContactActivity.class);
        context.startActivity(i);
    }
}
