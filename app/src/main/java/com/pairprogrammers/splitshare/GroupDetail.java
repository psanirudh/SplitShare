package com.pairprogrammers.splitshare;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pairprogrammers.splitshare.Adapters.RecyclerViewAdapter;
import com.pairprogrammers.splitshare.Adapters.TransactionRecyclerViewAdapter;
import com.pairprogrammers.splitshare.Models.Group;
import com.pairprogrammers.splitshare.Models.Transcation;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

public class GroupDetail extends AppCompatActivity {

    Group group;
    TextView tv;
    ArrayList<Transcation> transactions;
    RecyclerView transRv;
    TransactionRecyclerViewAdapter rvAdapter;

    DatabaseReference groupDetailReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_detail);
        tv = findViewById(R.id.title);
        transRv = findViewById(R.id.TransactionsList);
        transRv.setItemAnimator(new DefaultItemAnimator());
        transRv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        transactions = new ArrayList<Transcation>();
        rvAdapter = new TransactionRecyclerViewAdapter(this,transactions);//unused for now
        transRv.setAdapter(rvAdapter);

        Intent intent = getIntent();
        group = new Group();
        group.name = intent.getStringExtra("groupName");
        group.members  =  intent.getStringArrayListExtra("groupMembers");
        tv.setText(group.name);

        groupDetailReference = FirebaseDatabase.getInstance().getReference("groups").child(group.name).child("transactions");

        groupDetailReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Transcation newTransc = snapshot.getValue(Transcation.class);
                transactions.add(newTransc);
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

    public void tpage(View view) {
        Intent i = new Intent(this,AddTransaction.class);
        i.putExtra("groupName",group.name);
        startActivity(i);
    }

    public void tmembers(View view) {
        Intent i = new Intent(this,UserSelectionActivity.class);
        i.putExtra("groupName",group.name);
        i.putStringArrayListExtra("groupMembers",group.members);
        startActivity(i);
    }
}