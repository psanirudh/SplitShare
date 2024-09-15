package com.pairprogrammers.splitshare.Adapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pairprogrammers.splitshare.Models.Constants;
import com.pairprogrammers.splitshare.Models.Transcation;
import com.pairprogrammers.splitshare.R;

import java.util.ArrayList;

public class TransactionRecyclerViewAdapter  extends RecyclerView.Adapter<TransactionRecyclerViewAdapter.MyViewHold4er> {

    Context context;
    ArrayList<Transcation> items;

    public TransactionRecyclerViewAdapter(Context context,ArrayList<Transcation> items){
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
        holder.tv.setText(items.get(position).title);
         int netAmount = items.get(position).PaidTo.get(Constants.userName);
         if(netAmount==0){
             holder.subTitle.setTextColor(Color.parseColor("#808080"));
             holder.subTitle.setText("Not involved");
         }
         else if(netAmount>0){
             holder.subTitle.setTextColor(Color.parseColor("#8fce00"));
             holder.subTitle.setText("You get "+ netAmount);
         }
         else{
             holder.subTitle.setTextColor(Color.parseColor("#e50000"));
             holder.subTitle.setText("You owe "+ netAmount);
         }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MyViewHold4er extends RecyclerView.ViewHolder {
        TextView tv;
        TextView subTitle;
        public MyViewHold4er(View itemView) {
            super(itemView);
            // get the reference of item view's
            tv = (TextView) itemView.findViewById(R.id.itemTV);
            subTitle = (TextView) itemView.findViewById(R.id.subTitle);
        }
    }
}
