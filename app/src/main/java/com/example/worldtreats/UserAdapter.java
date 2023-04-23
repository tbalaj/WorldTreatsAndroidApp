package com.example.worldtreats;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.worldtreats.db.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private List<User> userList;
    private UserModel userModel;

    public UserAdapter(UserModel userModel) {
        this.userModel = userModel;
        this.userList = userModel.getUserList();
        registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                userList = userModel.getUserList();
            }
        });
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.usercard, parent, false);
        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userList.get(holder.getLayoutPosition());

        // Set the fields in the card item
        holder.usernameTextView.setText(user.getUsername());
        if (user.getUsername().equals("testuser1") || user.isAdmin()) {
            holder.deleteUserButton.setEnabled(false); // Disable the button
            holder.deleteUserButton.setBackgroundColor(Color.GRAY);
        } else {
            // Set a click listener for the "Delete User" button
            holder.deleteUserButton.setOnClickListener(view -> {
                int pos = holder.getLayoutPosition();
                User u = userList.get(pos);
                userModel.deleteUser(u);
                notifyDataSetChanged();
            });
        }
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }


    // ViewHolder class for the UserAdapter
    public class UserViewHolder extends RecyclerView.ViewHolder {
        public TextView usernameTextView;
        public Button deleteUserButton;

        public UserViewHolder(View view) {
            super(view);
            usernameTextView = view.findViewById(R.id.username);
            deleteUserButton = view.findViewById(R.id.deleteUser);
        }
    }
}
