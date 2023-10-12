package com.example.pandacare.PandaCareAccount;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface PandaUserDao {

        // insert data to database
        @Insert
        void registerUser(PandaUserEntity userEntity);

        // read all the username and password from the table
        @Query("SELECT *  from PandaCareUserAccount WHERE username = (:username) AND password =(:password)")
        PandaUserEntity login(String username, String password);

    }
    
