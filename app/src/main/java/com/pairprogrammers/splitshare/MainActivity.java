package com.pairprogrammers.splitshare;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pairprogrammers.splitshare.Adapters.RecyclerViewAdapter;
import com.pairprogrammers.splitshare.Models.Constants;
import com.pairprogrammers.splitshare.Models.Group;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    EditText sampleET;
    ArrayList<Group> groupsList;
    RecyclerView groupList;
     RecyclerViewAdapter rvAdapter;
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_NAME = "name";

    DatabaseReference firebaseGroupsEndPoint,firebaseUsersEndpoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        groupsList = new ArrayList<>();
        sampleET = findViewById(R.id.sampleInput);
        groupList = findViewById(R.id.GroupList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        groupList.setLayoutManager(layoutManager);
        groupList.setItemAnimator(new DefaultItemAnimator());

        rvAdapter = new RecyclerViewAdapter(this, groupsList);
        groupList.setAdapter(rvAdapter);
        firebaseGroupsEndPoint = FirebaseDatabase.getInstance().getReference("groups");


        firebaseGroupsEndPoint.f
    ..addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Object pp = snapshot.getValue();
                Group group = snapshot.getValue(Group.class);
                if(group.members.contains(Constants.userName)){
                    groupsList.add(group);
                    rvAdapter.notifyDataSetChanged();//notifyItemInserted(groupsList.size()-1);
                }
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

    public void UploadToCloud(View view) {
        String sampleTxt = sampleET.getText().toString();
        if(sampleTxt.isEmpty()){
            Toast.makeText(this,"Group can't  be empty",Toast.LENGTH_LONG).show();
            return;
        }
        String groupId = "group"+ Calendar.getInstance().toString()+":"+Constants.userName;
        Group newGroup = new Group();
        newGroup.members = new ArrayList<String>(){};
        newGroup.members.add(Constants.userName);
        newGroup.name = sampleTxt;
        DatabaseReference groupAddition = firebaseGroupsEndPoint.push();
        groupAddition.setValue(newGroup);
        Toast.makeText(this,sampleTxt+"@"+groupAddition.getKey(),Toast.LENGTH_LONG).show();

        firebaseUsersEndpoint = FirebaseDatabase.getInstance().getReference("users").child(Constants.userName);

        firebaseUsersEndpoint.child("groups").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                ArrayList<String> list;
                if (snapshot.exists()) {
                    list = (ArrayList<String>) snapshot.getValue();
                } else {
                    list = new ArrayList<>();
                }
                list.add(groupAddition.getKey());
                firebaseUsersEndpoint.child("groups").setValue(list);
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
    }
}