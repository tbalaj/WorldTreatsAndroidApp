package com.example.worldtreats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.worldtreats.db.AppDB;
import com.example.worldtreats.db.User;
import com.example.worldtreats.db.UserDAO;

public class LoginActivity extends AppCompatActivity {

    EditText usernameField, passwordField;
    Button loginButton;
    UserDAO userDao;


    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, LoginActivity.class);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userDao = AppDB.getInstance(this).getUserDAO();


        // HookUp Layout
        usernameField = findViewById(R.id.username);
        passwordField = findViewById(R.id.password);
        loginButton = findViewById(R.id.btnsignin);

        // Add Button Click Listeners
        loginButton.setOnClickListener(view -> {
            String username = usernameField.getText().toString();
            String password = passwordField.getText().toString();

            if (username.isEmpty() || password.isEmpty()) {
                Log.d("LoginActivity", "Username or password is empty");
                return;
            }
            User user = userDao.getUserByUsername(username);
            if (user == null) {
                Toast.makeText(this, "Username Not Available", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!user.getPassword().equals(password)) {
                Toast.makeText(this, "Incorrect password", Toast.LENGTH_SHORT).show();
                return;
            }

            SharedPreferences sharedPreferences = getSharedPreferences("authenticated", MODE_PRIVATE);
            sharedPreferences.edit().putString("username", username).apply();
            sharedPreferences.edit().putBoolean("isAuthenticated", true).apply();
            startActivity(HomeActivity.newIntent(this));
        });
    }
}