package com.pairprogrammers.splitshare;

import androidx.appcompat.app.AppCompatActivity;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText sampleET;
    ArrayList<String> items;
    ListView lv;
    ListAdapter listAdapter;

    DatabaseReference firebaseEndPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sampleET = findViewById(R.id.sampleInput);
        lv = findViewById(R.id.lv);

        firebaseEndPoint = FirebaseDatabase.getInstance().getReference("groups");
    }

    public void UploadToCloud(View view) {

        String sampleTxt = sampleET.getText().toString();
        firebaseEndPoint.child(sampleTxt).setValue(sampleTxt);
        Toast.makeText(this,sampleTxt,Toast.LENGTH_LONG).show();
    }
}