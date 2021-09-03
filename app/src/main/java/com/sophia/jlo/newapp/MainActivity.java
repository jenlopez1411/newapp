package com.sophia.jlo.newapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.List;
//Name: Jennifer S Lopez
//Date: 9/3/2021
//Title: Solo Project 01 Wk 01 CST 438
//Description: This project is connected with database so the user
//may login in and api.

public class MainActivity extends AppCompatActivity {
    public static String EXTRA_TEXT = "com.example.application.example.EXTRA_TEXT";
    //private TextView textViewResult;
    private EditText mUsernameField;
    private EditText mPasswordField;
    private String mUsername;
    private String mPassword;
    private Button mbtn;
    private UserViewModel userViewModel;
    List<User> users;
    private UserDao muserDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getDatabase();
        mUsernameField = findViewById(R.id.userNameEditText);
        mPasswordField = findViewById(R.id.passwordEditText);
        mbtn = findViewById(R.id.loginBtn);
        Log.v("now", String.valueOf(muserDao));

        users = muserDao.getAllUsers();

        if(users.size() <= 0){
            User u1 = new User("csumb1", "otter1");
            User u2 = new User("csumb2", "otter2");
            User u3 = new User("csumb3", "otter3");
            User u4 = new User("csumb4", "otter4");
            User u5 = new User("csumb5", "otter5");
            User u6 = new User("csumb6", "otter6");
            User u7 = new User("csumb7", "otter7");
            User u8 = new User("csumb8", "otter8");
            User u9 = new User("csumb9", "otter9");
            User u10 = new User("csumb10", "otter10");

            muserDao.insert(u1);
            muserDao.insert(u2);
            muserDao.insert(u3);
            muserDao.insert(u4);
            muserDao.insert(u5);
            muserDao.insert(u6);
            muserDao.insert(u7);
            muserDao.insert(u8);
            muserDao.insert(u9);
            muserDao.insert(u10);
        }
        mbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean userAcc = userCheck(mUsernameField, mPasswordField);
                if(userAcc){
                    Log.v("myAccount", "Valid Username/Password");

                    EditText editUsername = (EditText) findViewById(R.id.userNameEditText);
                    String text = editUsername.getText().toString();
//                    Intent landingPage = new Intent(MainActivity.this, LandingPage.class);
//                    landingPage.putExtra(EXTRA_TEXT, text);
//                    startActivity(landingPage);
                    //Factory Method
                    Intent intent = LandingPage.getIntent(getApplicationContext(), "W");
                    intent.putExtra(EXTRA_TEXT, text);
                    startActivity(intent);
                }
            }
        });
    }

    public boolean userCheck(EditText usernameField, EditText passwordField) {
        List<User> user = muserDao.getAllUsers();
        for(User Users: user){
            Log.v("my", "list" + Users.getId());
            if(Users.getUserName().equals(usernameField.getText().toString()) && Users.getPassword().equals(passwordField.getText().toString())){
                    return true;
            }else if(Users.getUserName().equals(usernameField.getText().toString()) && !(Users.getPassword().equals(passwordField.getText().toString()))){
                mPasswordField.setError("Wrong Password");
                return false;
            }else if(!Users.getUserName().equals(usernameField.getText().toString()) && (Users.getPassword().equals(passwordField.getText().toString()))){
                mUsernameField.setError("Wrong Username");
                return false;
            }
        }
        return false;
    }


    public void getDatabase(){
        muserDao = Room.databaseBuilder(this, UserDatabase.class, UserDatabase.DB_NAME)
                .allowMainThreadQueries()
                .build()
                .getUserDao();
    }

}