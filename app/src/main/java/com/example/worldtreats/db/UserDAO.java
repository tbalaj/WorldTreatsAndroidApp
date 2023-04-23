package com.example.worldtreats.db;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


@Dao
public interface UserDAO {
    @Query("SELECT * FROM User WHERE username = :username ")
    User getUserByUsername(String username);

    @Insert
    void insertUser(User user);

    @Query("SELECT * FROM User")
    List<User> getAllUsers();

    @Delete
    void deleteUser(User user);

    @Update
    void updateUser(User user);
}
