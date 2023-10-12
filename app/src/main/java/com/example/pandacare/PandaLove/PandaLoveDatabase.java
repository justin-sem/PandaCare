package com.example.pandacare.PandaLove;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

// Add annotation to database entities
@Database(entities = {PandaLoveModal.class}, version = 1)
public abstract class PandaLoveDatabase extends RoomDatabase {

    // create instance for the database class
    private static com.example.pandacare.PandaLove.PandaLoveDatabase instance;

    // create abstract variable for dao
    public abstract PandaLoveDao Dao();

    // getting instance for database
    public static synchronized com.example.pandacare.PandaLove.PandaLoveDatabase getInstance(Context context) {

        if (instance == null) {
            // creating a new instance if instance is null
            instance =
                    // to create a instance for our database here are creating a database builder
                    Room.databaseBuilder(context.getApplicationContext(),
                                    com.example.pandacare.PandaLove.PandaLoveDatabase.class, "PandaLoveDatabase")
                            .fallbackToDestructiveMigration()
                            //  add callback to the database
                            .addCallback(roomCallback).build();
        }
        return instance;
    }

    // create a callback for room database
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            // populate data
            new com.example.pandacare.PandaLove.PandaLoveDatabase.PopulateDbAsyncTask(instance).execute();
        }
    };

    // creating an async task class to perform task in background
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        PopulateDbAsyncTask(com.example.pandacare.PandaLove.PandaLoveDatabase instance) {
            PandaLoveDao dao = instance.Dao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}