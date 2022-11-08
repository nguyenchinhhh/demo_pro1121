package com.example.roominandroid.DAO;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.roominandroid.User;

@Database(entities = {User.class}, version = 1)
public abstract class UserData extends RoomDatabase {

    private static final String DATABASE_NAME = "user.db";
    private static UserData instance;

    public static synchronized UserData getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext() , UserData.class ,DATABASE_NAME)
                    .allowMainThreadQueries() // =?
                    .build();
        }
        return instance;
    }
    public abstract UserDAO userDAO();
}
