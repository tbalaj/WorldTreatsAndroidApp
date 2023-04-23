package com.example.worldtreats;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.worldtreats.db.AppDB;
import com.example.worldtreats.db.User;

public class AdminManageUsers extends AppCompatActivity {
    UserModel userModel;
    RecyclerView userRecyclerView;
    EditText usernameField, passwordField;
    CheckBox isAdminCheckBox;
    Button addUserButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_manage_users);
        System.out.println("AdminManageUsers.onCreate");
        //Hookup Layout
        usernameField = findViewById(R.id.usernameField);
        passwordField = findViewById(R.id.passwordField);
        addUserButton = findViewById(R.id.adminAddUserButton);
        isAdminCheckBox = findViewById(R.id.isAdminCheckBox);
        userModel = new ViewModelProvider(this, new ViewModelFactory(AppDB.getInstance(this))).get(UserModel.class);

        userRecyclerView = findViewById(R.id.user_recycler_view);
        userRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        UserAdapter userAdapter = new UserAdapter(userModel);
        userRecyclerView.setAdapter(userAdapter);


        addUserButton.setOnClickListener(view -> {
            String username = usernameField.getText().toString();
            String password = passwordField.getText().toString();

            userModel.insertUser(new User(username, password, isAdminCheckBox.isChecked()));
            userAdapter.notifyDataSetChanged();
        });
    }
}