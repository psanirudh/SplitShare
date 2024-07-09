package com.pairprogrammers.splitshare;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pairprogrammers.splitshare.Adapters.RecyclerViewAdapter;
import com.pairprogrammers.splitshare.Models.Group;

import java.util.ArrayList;

public class GroupDetail extends AppCompatActivity {

    Group group;
    TextView tv;
    ArrayList<String> transactions;
    RecyclerView transRv;
    RecyclerViewAdapter rvAdapter;

    DatabaseReference groupDetailReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_detail);
        tv = findViewById(R.id.title);
        transRv = findViewById(R.id.TransactionsList);
        transRv.setItemAnimator(new DefaultItemAnimator());
        transRv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        transactions = new ArrayList<String>();
        rvAdapter = new RecyclerViewAdapter(this,transactions);
        transRv.setAdapter(rvAdapter);

        Intent intent = getIntent();
        //group =  intent.getStringExtra("groupName");
        //Toast.makeText(this,"name is "+group.name,Toast.LENGTH_LONG).show();
        //tv.setText(groupName);


        groupDetailReference = FirebaseDatabase.getInstance().getReference("transactions").child("groupName");

        groupDetailReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                transactions.add(snapshot.getValue(String.class));
                rvAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}