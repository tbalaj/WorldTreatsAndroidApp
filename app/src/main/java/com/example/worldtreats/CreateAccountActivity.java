package com.example.worldtreats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.worldtreats.db.AppDB;
import com.example.worldtreats.db.User;
import com.example.worldtreats.db.UserDAO;

public class CreateAccountActivity extends AppCompatActivity {

    EditText usernameField, passwordField, repasswordField;
    Button registerButton;
    UserDAO userDao;


    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, CreateAccountActivity.class);
        return intent;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        //Get Database Instance
        userDao = AppDB.getInstance(this).getUserDAO();

        // HookUp Layout
        usernameField = findViewById(R.id.username);
        passwordField = findViewById(R.id.password);
        repasswordField = findViewById(R.id.repassword);
        registerButton = findViewById(R.id.btnsignup);

        // Add Button Click Listeners
        registerButton.setOnClickListener(view -> {
            String username = usernameField.getText().toString();
            String password = passwordField.getText().toString();
            String repassword = repasswordField.getText().toString();

            if (username.isEmpty() || password.isEmpty() || repassword.isEmpty()) {
                Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
                return;
            }
            User user = userDao.getUserByUsername(username);
            if (user != null) {
                Toast.makeText(this, "Username already exists", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!password.equals(repassword)) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                return;
            }
            userDao.insertUser(new User(username, password, false));
            startActivity(LoginActivity.newIntent(this));
        });
    }
}