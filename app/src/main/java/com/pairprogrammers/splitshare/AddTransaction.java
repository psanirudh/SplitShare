package com.pairprogrammers.splitshare;

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

import java.util.Calendar;

public class AddTransaction extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText desc,amt,date;
    Spinner paidBy;
    DatePickerDialog datePickerDialog;

    //Todo: Tested with temp data only need to modify this from db
    String []people={"dhana","sekar","hari","ani"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);
        desc=findViewById(R.id.description);
        amt = findViewById(R.id.amount);
        date = findViewById(R.id.date);
        paidBy = findViewById(R.id.amountPaidBy);
        paidBy.setOnItemSelectedListener(this);

        ArrayAdapter adapter = new ArrayAdapter(this , android.R.layout.simple_spinner_item ,people);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        paidBy.setAdapter(adapter);
    }

    public void addbtn(View view) {
        String tx= desc.getText().toString();
        Toast.makeText(this,tx,Toast.LENGTH_SHORT).show();
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
        Toast.makeText(this,people[pos],Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}