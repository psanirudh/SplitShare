package com.pairprogrammers.splitshare.Adapters;

import android.accessibilityservice.GestureDescription;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pairprogrammers.splitshare.GroupDetail;
import com.pairprogrammers.splitshare.Models.Group;
import com.pairprogrammers.splitshare.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    Context context;
    ArrayList<Group> items;

    public RecyclerViewAdapter(Context context,ArrayList<Group> items){
        this.context = context;
        this.items= items;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv.setText(items.get(position).name);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Group group = items.get(holder.getAdapterPosition());
                //Toast.makeText(context,"clicked "+groupName,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context,GroupDetail.class);
                intent.putExtra("groupName",group.name);
                intent.putStringArrayListExtra("groupMembers",group.members);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        public MyViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            tv = (TextView) itemView.findViewById(R.id.itemTV);
        }
    }
}
