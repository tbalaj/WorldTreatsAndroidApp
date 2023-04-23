package com.example.worldtreats.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


@Dao
public interface CartDAO {
    @Insert
    void insertCart(Cart cart);
    @Delete
    void deleteCart(Cart cart);
    @Update
    void updateCart(Cart cart);
    @Query("SELECT * FROM Cart where userId = :userId")
    List<Cart> getCartByUsername(int userId);
}
