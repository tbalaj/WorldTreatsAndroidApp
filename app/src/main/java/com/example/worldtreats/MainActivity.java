package com.example.worldtreats;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.worldtreats.db.AppDB;
import com.example.worldtreats.db.Product;
import com.example.worldtreats.db.ProductDAO;
import com.example.worldtreats.db.User;
import com.example.worldtreats.db.UserDAO;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    UserDAO userDao;
    SharedPreferences sharedPreferences;

    Button loginButton, createAccountButton;

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, MainActivity.class);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get Database Instance
        initDb();

        // Setup SharedPreferences
        sharedPreferences = this.getSharedPreferences("authenticated", Context.MODE_PRIVATE);
        boolean isAuthenticated = sharedPreferences.getBoolean("isAuthenticated", false);

        if (isAuthenticated) {
            startActivity(HomeActivity.newIntent(this));
        }

        // HookUp Layout
        loginButton = findViewById(R.id.login);
        createAccountButton = findViewById(R.id.createaccount);


        // Add Button Click Listeners
        loginButton.setOnClickListener(view -> {
            view.getContext().startActivity(LoginActivity.newIntent(view.getContext()));
        });

        createAccountButton.setOnClickListener(view -> {
            view.getContext().startActivity(CreateAccountActivity.newIntent(view.getContext()));
        });

    }

    void initDb() {
        UserDAO userDao = AppDB.getInstance(this).getUserDAO();
        ProductDAO productDAO = AppDB.getInstance(this).getProductDAO();
        if ( userDao.getUserByUsername("testuser1") == null){
            userDao.insertUser(new User("testuser1", "testuser1", false));
        }
        if ( userDao.getUserByUsername("admin2") == null){
            userDao.insertUser(new User("admin2", "admin2", true));
        }
        if (productDAO.getAllProducts().size() <= 0){
            ArrayList<Product> prodList = new ArrayList<>();
            prodList.add( new Product("Tacos!", "Better than they look!", 2.33, 76));
            prodList.add(new Product("Lindor Truffles", "The Best! <3", 9.56, 21));
            prodList.add(new Product("Cat Food", "Meowza!", 1.16, 31));
            prodList.add(new Product("Dog Food", "Mostly edible! Very afforable!", 10.10, 31));
            prodList.forEach(p->{
                productDAO.insertProduct(p);
            });
        }
    }
}