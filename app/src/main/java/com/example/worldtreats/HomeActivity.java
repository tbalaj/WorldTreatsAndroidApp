package com.example.worldtreats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.worldtreats.db.AppDB;

public class HomeActivity extends AppCompatActivity {
    TextView welcomeText;
    Button logoutButton, adminAreaButton, profileButton, browseButton;
    SharedPreferences sharedPreferences;

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, HomeActivity.class);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        System.out.printf("HomeActivity.onCreate()");
        //HookUp Layout
        welcomeText = findViewById(R.id.welcomeText);
        logoutButton = findViewById(R.id.logoutButton);
        adminAreaButton = findViewById(R.id.adminAreaButton);
        profileButton = findViewById(R.id.homeProfileButton);
        browseButton = findViewById(R.id.homeBrowseButton);
        // Setup SharedPreferences
        sharedPreferences = this.getSharedPreferences("authenticated", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "none");
        welcomeText.setText("Welcome " + username);

        if (AppDB.getInstance(this).getUserDAO().getUserByUsername(username).isAdmin() == true) {
            adminAreaButton.setVisibility(View.VISIBLE);
        }

        // Add Button Click Listeners
        logoutButton.setOnClickListener(view -> {
            sharedPreferences.edit().clear().apply();
            startActivity(MainActivity.newIntent(this));
        });
        adminAreaButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, AdminArea.class);
            this.startActivity(intent);
        });
        profileButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, UserProfile.class);
            this.startActivity(intent);
        });
        browseButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, Browse.class);
            this.startActivity(intent);
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        sharedPreferences = this.getSharedPreferences("authenticated", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "none");
        welcomeText.setText("Welcome " + username);
    }
}