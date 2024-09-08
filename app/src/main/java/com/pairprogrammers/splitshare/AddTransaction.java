package com.pairprogrammers.splitshare;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.pairprogrammers.splitshare.Models.Transcation;

import java.util.ArrayList;
import java.util.Calendar;

public class AddTransaction extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText desc,amt,date;
    Spinner paidBy;
    DatePickerDialog datePickerDialog;

    String groupName;
    public int payer;

    //Todo: Tested with temp data only need to modify this from db
    ArrayList<String> groupMembers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        groupMembers = new ArrayList<String>();


        setContentView(R.layout.activity_add_transaction);
        desc=findViewById(R.id.description);
        amt = findViewById(R.id.amount);
        date = findViewById(R.id.date);
        paidBy = findViewById(R.id.amountPaidBy);
        paidBy.setOnItemSelectedListener(this);
        groupName = getIntent().getStringExtra("groupName");

        ArrayAdapter adapter = new ArrayAdapter(this , android.R.layout.simple_spinner_item , groupMembers);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        paidBy.setAdapter(adapter);

        FirebaseDatabase.getInstance().getReference("groups").child(groupName).child("members").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String groupMember = snapshot.getValue(String.class);
                groupMembers.add(groupMember);
                adapter.notifyDataSetChanged();
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

    public void addBtn(View view) {
        Transcation transcation = new Transcation();
        transcation.title = desc.getText().toString();
        transcation.totAmountPaid = Integer.parseInt(amt.getText().toString());
        transcation.PaidBy = groupMembers.get(payer);
        transcation.Calculate(groupMembers);
        FirebaseDatabase.getInstance().getReference("groups").child(groupName).child("transactions").child(transcation.tid).setValue(transcation);


        }

    public void cancelbtn(View view) {

        String tx= desc.getText().toString();
        Toast.makeText(this,tx,Toast.LENGTH_SHORT).show();
    }

    public void currencyChange(View view) {


    }

    public void changeDate(View view) {
        Calendar today = Calendar.getInstance();
        int day = today.get(Calendar.DAY_OF_MONTH);
        int month = today.get(Calendar.MONTH);
        int year = today.get(Calendar.YEAR);
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                date.setText(day+"/"+(month+1)+"/"+year);
            }
        },year,month,day);
        datePickerDialog.show();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
        payer = pos;
        Toast.makeText(this, groupMembers.get(pos),Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}