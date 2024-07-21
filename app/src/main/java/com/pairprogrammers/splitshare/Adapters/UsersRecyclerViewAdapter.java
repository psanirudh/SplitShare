package com.pairprogrammers.splitshare.Adapters;

import android.accessibilityservice.GestureDescription;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pairprogrammers.splitshare.GroupDetail;
import com.pairprogrammers.splitshare.Models.Group;
import com.pairprogrammers.splitshare.Models.UserSelection;
import com.pairprogrammers.splitshare.R;

import java.util.ArrayList;



public class UsersRecyclerViewAdapter extends RecyclerView.Adapter<UsersRecyclerViewAdapter.MyViewHolder2> {


    Context context;
    ArrayList<UserSelection> items;

    public UsersRecyclerViewAdapter(Context context,ArrayList<UserSelection> items){
        this.context = context;
        this.items= items;
    }


    @NonNull
    @Override
    public MyViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_selection_item,parent,false);
        return new MyViewHolder2(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder2 holder, int position) {
        if(holder!=null){
             holder.groupName.setText(items.get(position).name);
             holder.isSelected.setChecked(items.get(position).isSelected);
             holder.isSelected.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     items.get(holder.getAdapterPosition()).isSelected = !items.get(holder.getAdapterPosition()).isSelected;
                 }
             });


        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MyViewHolder2 extends RecyclerView.ViewHolder {
        TextView groupName;
        CheckBox isSelected;
        public MyViewHolder2(View itemView) {
            super(itemView);
            groupName = (TextView) itemView.findViewById(R.id.userName);
            isSelected = (CheckBox) itemView.findViewById(R.id.userSelection);
        }
    }
}
