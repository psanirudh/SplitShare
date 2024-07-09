package com.pairprogrammers.splitshare;

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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pairprogrammers.splitshare.Models.Constants;
import com.pairprogrammers.splitshare.Models.Group;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class LoginActivity extends AppCompatActivity {

    EditText editText_username;
    Button buttonlogin,buttonsignup;
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_NAME = "name";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editText_username=findViewById(R.id.username);
        buttonlogin=findViewById(R.id.login);
        buttonsignup=findViewById(R.id.signup);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        String name = sharedPreferences.getString(KEY_NAME,null);
        if (name!=null){
            Constants.userName = name;
            Intent intent= new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
        }

        buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String enteredUserName = editText_username.getText().toString();

                if(!doesUserExist(enteredUserName)){
                    Toast.makeText(getApplicationContext(),"No such user exist",Toast.LENGTH_LONG).show();
                    return;
                }
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_NAME,enteredUserName);
                editor.apply();

                Constants.userName = enteredUserName;

                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);

            }
        });

        buttonsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String enteredUserName = editText_username.getText().toString();

                if(!doesUserExist(enteredUserName)){
                    Toast.makeText(getApplicationContext(),"This username is taken,try some other name",Toast.LENGTH_LONG).show();
                    return;
                }
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_NAME,enteredUserName);
                editor.apply();

                Constants.userName = enteredUserName;

                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);


            }
        });

    }

    public  boolean addUser(String userName){
      /*  String sampleTxt = sampleET.getText().toString();
        Group newGroup = new Group();
        newGroup.members = new ArrayList<String>(){};
        newGroup.members.add(Constants.userName);
        newGroup.name = sampleTxt;
        firebaseGroupsEndPoint.child(sampleTxt).setValue(newGroup);
        Toast.makeText(this,sampleTxt,Toast.LENGTH_LONG).show();*/

    }

    public boolean doesUserExist(String userName){
        DatabaseReference firebaseUsersEndpoint = FirebaseDatabase.getInstance().getReference("users").child(userName);

        //firebaseUsersEndpoint.

        return true;
    }
}