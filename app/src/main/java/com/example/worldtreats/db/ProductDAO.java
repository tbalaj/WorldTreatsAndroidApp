package com.example.worldtreats.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProductDAO {
    @Query("Select * from Product where name = :name")
    Product getProductByName(String name);

    @Insert
    void insertProduct(Product product);

    @Update
    void updateProduct(Product product);

    @Query("SELECT * FROM Product")
    List<Product> getAllProducts();

    @Delete
    void deleteProduct(Product product);
}
