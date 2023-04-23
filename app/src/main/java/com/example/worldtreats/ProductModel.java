package com.example.worldtreats;

import androidx.lifecycle.ViewModel;

import com.example.worldtreats.db.AppDB;
import com.example.worldtreats.db.Product;
import com.example.worldtreats.db.ProductDAO;

import java.util.List;

public class ProductModel extends ViewModel {
    private List<Product> productList;
    private ProductDAO productDao;
    public ProductModel(AppDB appDB){
        productDao = appDB.getProductDAO();
        productList = productDao.getAllProducts();
    }
    public Product getProductByName(String name){
        return productDao.getProductByName(name);
    }
    public void insertProduct(Product product){
        productDao.insertProduct(product);
        updateProductList();
    }

    public void deleteProduct(Product product){
        productDao.deleteProduct(product);
        updateProductList();
    }

    private void updateProductList() {
        productList = productDao.getAllProducts();
    }

    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }

    public void updateProduct(Product p) {
        productDao.updateProduct(p);
        updateProductList();
    }
}
