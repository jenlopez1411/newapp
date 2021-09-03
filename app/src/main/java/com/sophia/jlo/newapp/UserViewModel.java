package com.sophia.jlo.newapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.List;

public class UserViewModel extends AndroidViewModel {
    private UserRepository repository;
    private List<User> allUsers;


    public UserViewModel(@NonNull Application application) {
        super(application);
        repository = new UserRepository(application);
        allUsers = repository.getAllUsers();
    }

    public void insert(User user){
        repository.insert(user);
    }
    public void update(User user){
        repository.update(user);
    }
    public void delete(User user){
        repository.delete(user);
    }
    public void deleteAllUsers(){
        repository.deleteAllUsers();
    }

    public List<User> getAllUsers() {
        return allUsers;
    }
}
