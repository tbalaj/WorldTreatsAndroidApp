package com.example.worldtreats;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.worldtreats.db.AppDB;
import com.example.worldtreats.db.Product;

public class AdminManageInventory extends AppCompatActivity {

    Button addProductButton;
    EditText productNameField, productPriceField, productQuantityField, productDescriptionField;
    RecyclerView recyclerView;
    ProductModel productModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_manage_inventory);
        //Hookup Layout
        productNameField = findViewById(R.id.adminAddProductName);
        productPriceField = findViewById(R.id.adminAddItemPrice);
        productQuantityField = findViewById(R.id.adminAddItemQty);
        productDescriptionField = findViewById(R.id.adminAddItemDescription);
        addProductButton = findViewById(R.id.adminAddItemButton);

        recyclerView = findViewById(R.id.adminManageInventoryRecyclerView);
        productModel = new ViewModelProvider(this, new ViewModelFactory(AppDB.getInstance(this))).get(ProductModel.class);
        ProductAdapter adapter = new ProductAdapter(productModel);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        addProductButton.setOnClickListener(view -> {
            String productName = productNameField.getText().toString();
            Double productPrice = Double.valueOf(productPriceField.getText().toString());
            String productDescription = productDescriptionField.getText().toString();
            Integer productQuantity = Integer.valueOf( productQuantityField.getText().toString());
            productModel.insertProduct(new Product(productName, productDescription, productPrice, productQuantity));
            adapter.notifyDataSetChanged();
            Toast.makeText(this, productName + " added.", Toast.LENGTH_SHORT).show();
        });
    }
}