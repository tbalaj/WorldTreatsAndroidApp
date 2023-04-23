package com.example.worldtreats;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.worldtreats.db.AppDB;
import com.example.worldtreats.db.User;

public class UserProfile extends AppCompatActivity {
    EditText profileUsername, profilePassword;
    Button profileUpdateButton, profileDeleteButton;
    String username;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        // Hookup layout elements
        profileUsername = findViewById(R.id.profileUsername);
        profilePassword = findViewById(R.id.profilePassword);
        profileUpdateButton = findViewById(R.id.profileUpdateButton);
        profileDeleteButton = findViewById(R.id.profileDeleteButton);
        sharedPreferences = getSharedPreferences("authenticated", Context.MODE_PRIVATE);

        // Get username from SharedPreferences
        username = sharedPreferences.getString("username", "Error!");
        User u = AppDB.getInstance(this).getUserDAO().getUserByUsername(username);
        profileUsername.setText(u.getUsername());
        profilePassword.setText(u.getPassword());


        // add onclick to buttons
        profileUpdateButton.setOnClickListener(view -> {
            String uname = profileUsername.getText().toString();
            String upass = profilePassword.getText().toString();
            User user = AppDB.getInstance(this).getUserDAO().getUserByUsername(username);
            user.setUsername(uname);
            user.setPassword(upass);
            AppDB.getInstance(this).getUserDAO().updateUser(user);
            username = uname;
            sharedPreferences.edit().putString("username", username).apply();


            });

        profileDeleteButton.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Confirmation");
            builder.setMessage("Are you sure you want to perform this action?");
            builder.setPositiveButton("Yes, please!", (dialog, which) -> {
                User user = AppDB.getInstance(this).getUserDAO().getUserByUsername(username);
                AppDB.getInstance(this).getUserDAO().deleteUser(user);
                this.getSharedPreferences("authenticated", MODE_PRIVATE).edit().clear().apply();
                this.startActivity( MainActivity.newIntent(this));
                Toast.makeText(this, "User deleted!", Toast.LENGTH_SHORT).show();
            });
            builder.setNegativeButton("Cancel", (dialog, which) -> {
                // Do nothing
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        });
    }
}