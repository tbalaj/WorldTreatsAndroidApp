package com.example.worldtreats;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.worldtreats.db.AppDB;

public class AdminArea extends AppCompatActivity {
    Button manageUsersButton, manageInventoryButton;
    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, AdminArea.class);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_area);

        manageUsersButton = findViewById(R.id.adminManageUsersButton);
        manageInventoryButton = findViewById(R.id.adminManageInventoryButton);


        manageUsersButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, AdminManageUsers.class);
            this.startActivity(intent);
        });
        manageInventoryButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, AdminManageInventory.class);
            this.startActivity(intent);
        });
    }
}