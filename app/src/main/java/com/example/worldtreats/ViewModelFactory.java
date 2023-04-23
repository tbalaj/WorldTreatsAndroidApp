package com.example.worldtreats;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.worldtreats.db.AppDB;

public class ViewModelFactory implements ViewModelProvider.Factory {
    private final AppDB appDB;

    public ViewModelFactory(AppDB appDB) {
        this.appDB = appDB;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(UserModel.class)) {
            return (T) new UserModel(appDB);
        }
        if (modelClass.isAssignableFrom(ProductModel.class)) {
            return (T) new ProductModel(appDB);
        }
        if (modelClass.isAssignableFrom(BrowseModel.class)) {
            return (T) new BrowseModel(appDB);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.toString());
    }
}