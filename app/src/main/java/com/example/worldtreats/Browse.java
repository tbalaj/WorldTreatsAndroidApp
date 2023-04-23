package com.example.worldtreats;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.worldtreats.db.AppDB;

public class Browse extends AppCompatActivity {
    RecyclerView recyclerView;
    BrowseModel browseModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);
        browseModel = new ViewModelProvider(this, new ViewModelFactory(AppDB.getInstance(this))).get(BrowseModel.class);

        String username = this
                .getSharedPreferences("authenticated", Context.MODE_PRIVATE)
                .getString("username", "none");
        recyclerView = findViewById(R.id.browseRecyclerView);

        BrowseAdapter browseAdapter = new BrowseAdapter(browseModel, username);
        recyclerView.setAdapter(browseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}