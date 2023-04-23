package com.example.worldtreats;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.worldtreats.db.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> productList;
    private ProductModel productModel;

    public ProductAdapter(ProductModel productModel) {
        this.productModel = productModel;
        this.productList = productModel.getAllProducts();
        registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                productList = productModel.getAllProducts();
            }
        });
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.product_card, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.productName.setText(product.getName());
        holder.productDescription.setText(product.getDescription());
        holder.productPrice.setText(String.valueOf(product.getPrice()));
        holder.productQty.setText(String.valueOf(product.getQuantity()));
        holder.updateProduct.setOnClickListener(view -> {
            int pos = holder.getLayoutPosition();
            Product p = productList.get(pos);
            p.setName(holder.productName.getText().toString());
            p.setDescription(holder.productDescription.getText().toString());
            p.setPrice(Double.valueOf(holder.productPrice.getText().toString()));
            p.setQuantity(Integer.valueOf(holder.productQty.getText().toString()));
            productModel.updateProduct(p);
            notifyDataSetChanged();
            Toast.makeText(view.getContext(), p.getName()  + " updated", Toast.LENGTH_SHORT).show();
        });
        holder.deleteProduct.setOnClickListener(view -> {
            int pos = holder.getLayoutPosition();
            Product p = productList.get(pos);
            productModel.deleteProduct(p);
            notifyDataSetChanged();
            Toast.makeText(view.getContext(), p.getName()  + " deleted", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {

        public TextView productName, productDescription, productQty,productPrice;
        public Button updateProduct, deleteProduct;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.adminEditItemName);
            productDescription = itemView.findViewById(R.id.adminEditItemDescription);
            productPrice = itemView.findViewById(R.id.adminEditItemPrice);
            productQty = itemView.findViewById(R.id.adminEditItemQty);
            updateProduct = itemView.findViewById(R.id.adminUpdateItemButton);
            deleteProduct = itemView.findViewById(R.id.adminDeleteItemButton);
        }
    }
}