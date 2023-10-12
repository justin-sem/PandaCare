package com.example.pandacare.PandaCareAccount;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

//Add annotation to database entities
@Database(entities = {PandaUserEntity.class}, version = 1)
public abstract class PandaUserDatabase extends RoomDatabase {

    private static final String databaseName = "PandaCareUserAccount";
    private static PandaUserDatabase userDB;

    // create abstract variable for dao
    public abstract PandaUserDao userDao();

    //getting user instance for database
    public static synchronized PandaUserDatabase getUserDB(Context context){
        // creating a new userDB if instance is null
        if(userDB == null){
            userDB = Room.databaseBuilder(context, PandaUserDatabase.class,databaseName)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return userDB;
    }

}
