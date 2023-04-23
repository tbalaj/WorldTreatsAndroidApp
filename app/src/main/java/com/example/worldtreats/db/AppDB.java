package com.example.worldtreats.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class, Cart.class, Product.class}, version = 1)
public abstract class AppDB extends RoomDatabase {
    public static String DB_NAME = "AppDB";
    public static String USER_TABLE = "USER";
    public static String ITEM_TABLE = "ITEM";
    public static String CART_TABLE = "CART";
    private static AppDB instance;
    public abstract UserDAO getUserDAO();
    public abstract ProductDAO getProductDAO();
    public abstract CartDAO getCartDAO();
    public static synchronized AppDB getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDB.class, "AppDB")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }


}
