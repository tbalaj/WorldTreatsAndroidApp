package com.example.worldtreats;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.worldtreats.db.AppDB;
import com.example.worldtreats.db.User;
import com.example.worldtreats.db.UserDAO;

import java.util.List;

public class UserModel extends ViewModel {
    private User user;
    private List<User> userList;
    private UserDAO userDao;
    public UserModel( AppDB appDB){
        userDao = appDB.getUserDAO();
        userList = userDao.getAllUsers();
    }

    public User getUser() {
        return user;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void insertUser(User user) {
        userDao.insertUser(user);
        updateUserList();
    }

    public void deleteUser(User user) {
        userDao.deleteUser(user);
        updateUserList();
    }
    public void updateUserList(){
        userList = userDao.getAllUsers();
    }
}
