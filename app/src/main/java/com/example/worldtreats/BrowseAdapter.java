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

public class BrowseAdapter extends RecyclerView.Adapter<BrowseAdapter.BrowseViewHolder> {
    private List<Product> productList;
    private BrowseModel browseModel;

    public BrowseAdapter(BrowseModel browseModel, String username) {
        this.browseModel = browseModel;
        this.productList = browseModel.getProductList();
        browseModel.setUserByUsername(username);
        registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                productList = browseModel.getProductList();
            }
        });
    }

    @NonNull
    @Override
    public BrowseAdapter.BrowseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.browseitem, parent, false);
        return new BrowseAdapter.BrowseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BrowseAdapter.BrowseViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.productNameTextView.setText(product.getName());
        holder.productDescTextView.setText(product.getDescription());
        holder.productPriceTextView.setText("$" + product.getPrice());
        holder.browseAddItemButton.setOnClickListener(view -> {
            int pos = holder.getLayoutPosition();
            Product p = productList.get(pos);
            browseModel.insertProductToCart(p);

            Toast.makeText(view.getContext(), "Added " + p.getName() + " to cart", Toast.LENGTH_SHORT).show();
        });

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class BrowseViewHolder extends RecyclerView.ViewHolder {
        public TextView productNameTextView, productDescTextView, productPriceTextView;
        public Button browseAddItemButton;

        public BrowseViewHolder(View view) {
            super(view);
            productNameTextView = view.findViewById(R.id.broseProductName);
            productDescTextView = view.findViewById(R.id.broseProductDescription);
            productPriceTextView = view.findViewById(R.id.browseProductPrice);
            browseAddItemButton = view.findViewById(R.id.browseAddItem);
        }
    }
}
