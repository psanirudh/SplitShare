package com.pairprogrammers.splitshare;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pairprogrammers.splitshare.Adapters.RecyclerViewAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText sampleET;
    ArrayList<String> items;
    RecyclerView groupList;
     RecyclerViewAdapter rvAdapter;

    DatabaseReference firebaseEndPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        items = new ArrayList<>();
        sampleET = findViewById(R.id.sampleInput);
        groupList = findViewById(R.id.GroupList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        groupList.setLayoutManager(layoutManager);
        groupList.setItemAnimator(new DefaultItemAnimator());

        rvAdapter = new RecyclerViewAdapter(this,items);
        groupList.setAdapter(rvAdapter);
        firebaseEndPoint = FirebaseDatabase.getInstance().getReference("groups");

        firebaseEndPoint.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String datum = snapshot.getValue(String.class);
                items.add(datum);
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

    public void UploadToCloud(View view) {

        String sampleTxt = sampleET.getText().toString();
        firebaseEndPoint.child(sampleTxt).setValue(sampleTxt);
        Toast.makeText(this,sampleTxt,Toast.LENGTH_LONG).show();
    }
}