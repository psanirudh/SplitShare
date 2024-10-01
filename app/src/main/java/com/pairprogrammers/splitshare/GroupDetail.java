package com.pairprogrammers.splitshare;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
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
import com.pairprogrammers.splitshare.Models.Constants;
import com.pairprogrammers.splitshare.Models.Group;
import com.pairprogrammers.splitshare.Models.Transcation;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

public class GroupDetail extends AppCompatActivity {

    Group group;
    TextView tv,overallTally;
    ArrayList<Transcation> transactions;
    RecyclerView transRv;
    TransactionRecyclerViewAdapter rvAdapter;
    Map<String, Integer> balanceOfCurrentUser;

    DatabaseReference groupDetailReference;
    Context c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_detail);
        balanceOfCurrentUser = new HashMap<>();
        tv = findViewById(R.id.title);
        overallTally = findViewById(R.id.overViewBalance);
        transRv = findViewById(R.id.TransactionsList);
        transRv.setItemAnimator(new DefaultItemAnimator());
        transRv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        transactions = new ArrayList<Transcation>();
        rvAdapter = new TransactionRecyclerViewAdapter(this,transactions);//unused for now
        transRv.setAdapter(rvAdapter);
        c = getApplicationContext();
        Intent intent = getIntent();
        group = new Group();
        group.name = intent.getStringExtra("groupName");
        group.members  =  intent.getStringArrayListExtra("groupMembers");
        tv.setText(group.name);

        groupDetailReference = FirebaseDatabase.getInstance().getReference("groups").child(group.name).child("transactions");

        groupDetailReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Toast.makeText(c,"ef",Toast.LENGTH_LONG).show();
                Transcation newTransc = snapshot.getValue(Transcation.class);

                if(newTransc.PaidTo.containsKey(Constants.userName) && newTransc.PaidTo.get(Constants.userName) !=0){
                    Boolean isPositive = newTransc.PaidTo.get(Constants.userName) > 0;
                    if(isPositive) {
                        for (String person : newTransc.PaidTo.keySet()) {
                            if(person.equals(Constants.userName))
                                continue;
                            if(!balanceOfCurrentUser.containsKey(person))
                                balanceOfCurrentUser.put(person,0);

                            int currentValue = balanceOfCurrentUser.get(person);
                            currentValue = currentValue + (-1*newTransc.PaidTo.get(person));
                            balanceOfCurrentUser.put(person,currentValue);
                        }
                    }
                    else{
                        if(!balanceOfCurrentUser.containsKey(newTransc.PaidBy))
                            balanceOfCurrentUser.put(newTransc.PaidBy,0);
                        int currentValue = balanceOfCurrentUser.get(newTransc.PaidBy);
                        currentValue += newTransc.PaidTo.get(Constants.userName);
                        balanceOfCurrentUser.put(newTransc.PaidBy, currentValue);
                    }
                }


                transactions.add(newTransc);
                rvAdapter.notifyDataSetChanged();

                StringBuilder s = new StringBuilder();
                s.append(balanceOfCurrentUser.keySet().size()+" ::");
                for (String eachUser : balanceOfCurrentUser.keySet()) {

                    if(eachUser.equals(Constants.userName)){
                        Toast.makeText(c,"same use",Toast.LENGTH_LONG).show();
                        continue;
                    }

                    if(balanceOfCurrentUser.get(eachUser)>0){
                        s.append( eachUser +" owes you Rs"+balanceOfCurrentUser.get(eachUser));
                    }
                    else if(balanceOfCurrentUser.get(eachUser)<0){
                        s.append(" You owe " +eachUser + " Rs"+balanceOfCurrentUser.get(eachUser));
                    }
                    s.append("   ");
                }
                overallTally.setText(s.toString());



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
        Constants.currentTransaction = null;
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