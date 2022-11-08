package com.example.roominandroid;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private Context context;
    private List<User> list = new ArrayList<>();

//    public UserAdapter() {
//    }

    private ClickItem clickItem;

    public interface ClickItem {
        void updateUser(User user);
    }



    public UserAdapter(Context context, List<User> list, ClickItem clickItem) {
        this.context = context;
        this.list = list;
        this.clickItem = clickItem;
    }

    public void setData(ArrayList<User> lissU) {
        this.list = lissU;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {
        User user = list.get(position);
        if (user == null) {
            return;
        }
        holder.tvName.setText("Name: " + list.get(holder.getAdapterPosition()).getName());
        holder.tvAddress.setText("Address: " + list.get(holder.getAdapterPosition()).getAddress());
        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickItem.updateUser(user);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvAddress;
        Button btnUpdate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAddress = itemView.findViewById(R.id.tvAddress);
            tvName = itemView.findViewById(R.id.tvName);
            btnUpdate = itemView.findViewById(R.id.btnUpdate);
        }
    }
}
