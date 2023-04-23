package com.example.worldtreats;

import androidx.lifecycle.ViewModel;

import com.example.worldtreats.db.AppDB;
import com.example.worldtreats.db.Cart;
import com.example.worldtreats.db.CartDAO;
import com.example.worldtreats.db.Product;
import com.example.worldtreats.db.ProductDAO;
import com.example.worldtreats.db.User;

import java.util.List;

public class BrowseModel extends ViewModel {
    private CartDAO cartDAO;
    private ProductDAO productDAO;
    private User user;
    private Cart cart;
    private List<Product> productList;
    private AppDB appDB;
    public BrowseModel(AppDB appDB){
        this.appDB = appDB;
        cartDAO = appDB.getCartDAO();
        productDAO = appDB.getProductDAO();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public void setUserByUsername(String username){
        user = appDB.getUserDAO().getUserByUsername(username);
    }

    public CartDAO getCartDAO() {
        return cartDAO;
    }

    public void setCartDAO(CartDAO cartDAO) {
        this.cartDAO = cartDAO;
    }

    public ProductDAO getProductDAO() {
        return productDAO;
    }

    public void setProductDAO(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public List<Product> getProductList() {
        return productDAO.getAllProducts();
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public void insertProductToCart(Product p) {
        Cart c = new Cart(user.getId(), p.getId());
        cartDAO.insertCart(c);
    }
}
