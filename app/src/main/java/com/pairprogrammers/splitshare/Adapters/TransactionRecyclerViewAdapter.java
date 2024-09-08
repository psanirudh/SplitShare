package com.pairprogrammers.splitshare.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pairprogrammers.splitshare.R;

import java.util.ArrayList;

public class TransactionRecyclerViewAdapter  extends RecyclerView.Adapter<TransactionRecyclerViewAdapter.MyViewHold4er> {

    Context context;
    ArrayList<String> items;

    public TransactionRecyclerViewAdapter(Context context,ArrayList<String> items){
        this.context = context;
        this.items= items;
    }


    @NonNull
    @Override
    public MyViewHold4er onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item_layout,parent,false);
        return new MyViewHold4er(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHold4er holder, int position) {
        holder.tv.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MyViewHold4er extends RecyclerView.ViewHolder {
        TextView tv;
        public MyViewHold4er(View itemView) {
            super(itemView);
            // get the reference of item view's
            tv = (TextView) itemView.findViewById(R.id.itemTV);
        }
    }
}
