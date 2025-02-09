package com.pairprogrammers.splitshare;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pairprogrammers.splitshare.Models.Constants;
import com.pairprogrammers.splitshare.Models.Group;
import com.pairprogrammers.splitshare.Models.UserSelection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

public class LoginActivity extends AppCompatActivity {

    EditText editText_username;
    Button buttonlogin,buttonsignup;
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_NAME = "name";
    DatabaseReference firebaseUsersEndpoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseUsersEndpoint = FirebaseDatabase.getInstance().getReference("users");

        editText_username=findViewById(R.id.username);
        buttonlogin=findViewById(R.id.login);
        buttonsignup=findViewById(R.id.signup);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        String name = sharedPreferences.getString(KEY_NAME,null);
        if (name!=null){
            Constants.userName = name;
            navigateToMainPage();
        }

        buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String enteredUserName = editText_username.getText().toString();
                DatabaseReference particularUsersEndpoint = firebaseUsersEndpoint.child(enteredUserName);
                particularUsersEndpoint.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString(KEY_NAME, enteredUserName);
                            editor.apply();
                            Constants.userName = enteredUserName;
                            navigateToMainPage();
                        } else {
                            Toast.makeText(getApplicationContext(), "No such user exist", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }});

        buttonsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String enteredUserName = editText_username.getText().toString();
                DatabaseReference particularUserEndPoint =  firebaseUsersEndpoint.child(enteredUserName);
                particularUserEndPoint.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            Toast.makeText(getApplicationContext(), "Sorry ;) username already taken", Toast.LENGTH_LONG).show();
                        }
                        else{
                            UserSelection user = new UserSelection();
                            user.name = enteredUserName;
                            user.groups = new ArrayList<String>();
                            particularUserEndPoint.setValue(user);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString(KEY_NAME, enteredUserName);
                            editor.apply();
                            Constants.userName = enteredUserName;
                            navigateToMainPage();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

    }

    public void navigateToMainPage(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}