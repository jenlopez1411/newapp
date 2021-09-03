package com.sophia.jlo.newapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    @Query("DELETE FROM user_table")
    void deleteAllUsers();

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    List<User> getAllUsers();

    @Query("SELECT * FROM user_table WHERE username = :username")
    User getUser(String username);

    @Query("SELECT * FROM user_table WHERE password = :password")
    User getPassword(String password);
}
