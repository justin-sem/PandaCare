package com.example.pandacare.PandaFit;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

// Add annotation to database entities
@Database(entities = {PandaFitModal.class}, version = 1)
public abstract class PandaFitDatabase extends RoomDatabase {

    // create instance for our database class
    private static PandaFitDatabase instance;

    // create abstract variable for dao
    public abstract PandaFitDao Dao();

    // getting instance for database
    public static synchronized PandaFitDatabase getInstance(Context context) {

        if (instance == null) {
           // creating a new instance if instance is null
            instance =
                    // to create a instance for database here are creating a database builder
                    Room.databaseBuilder(context.getApplicationContext(),
                                    PandaFitDatabase.class, "PandaFitDatabase")
                            .fallbackToDestructiveMigration()
                            // add callback to the database.
                            .addCallback(roomCallback).build();
        }
        return instance;
    }

    // create a callback for the room database
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            // populate data
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    // creating an async task class to perform task in background
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        PopulateDbAsyncTask(PandaFitDatabase instance) {
            PandaFitDao dao = instance.Dao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
