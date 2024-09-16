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
import com.pairprogrammers.splitshare.Adapters.UsersRecyclerViewAdapter;
import com.pairprogrammers.splitshare.Models.Group;
import com.pairprogrammers.splitshare.Models.UserSelection;

import java.util.ArrayList;

public class UserSelectionActivity extends AppCompatActivity {

    UsersRecyclerViewAdapter recyclerViewAdapter;
    RecyclerView recyclerView;
    ArrayList<UserSelection> users;
    Group group;
    TextView textView;
    DatabaseReference userDetailRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_selection);
        textView=findViewById(R.id.AddMemberGrp);

        recyclerView = findViewById(R.id.userSelectionRV);

        Intent intent = getIntent();
        group = new Group();
        group.name = intent.getStringExtra("groupName");
        group.members  =  intent.getStringArrayListExtra("groupMembers");
        textView.setText(group.name);



        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        users = new ArrayList<UserSelection>();

        recyclerViewAdapter = new UsersRecyclerViewAdapter(this, users,1);
        recyclerView.setAdapter(recyclerViewAdapter);

        userDetailRef = FirebaseDatabase.getInstance().getReference("users");

         userDetailRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String userNameFromCloud = snapshot.getKey();
                boolean isSelected = false;
                if(group.members.contains(userNameFromCloud)){
                    isSelected= true;
                }
                users.add(new UserSelection(userNameFromCloud,isSelected));
                recyclerViewAdapter.notifyDataSetChanged();
                //rvAdapter.notifyDataSetChanged();
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


    public void addMembers(View v){

        ArrayList<String> updateMembers = new ArrayList<>();
        for (UserSelection u : users) {
            if(u.isSelected)
              updateMembers.add(u.name);
        }

        FirebaseDatabase.getInstance().getReference("groups")
                .child(group.name).child("members").setValue(updateMembers);
        Toast.makeText(this,"member(s) modified",Toast.LENGTH_LONG).show();
        finish();

    }
}