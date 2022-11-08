package com.example.roominandroid.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.roominandroid.User;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface UserDAO {

    @Insert
    void insertUser(User user);

    @Query("SELECT * FROM USER")
    List<User> getListUser();

    @Query("Select * from USER where name = :userName")
    List<User> checkUser(String userName);

    @Update
    void updateUser(User user);
}
