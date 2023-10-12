package com.example.pandacare.PandaLove;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class PandaLoveRepo {

    private PandaLoveDao dao;
    private LiveData<List<PandaLoveModal>> allHabits;

    // creating a constructor for our variables
    public PandaLoveRepo(Application application) {
        PandaLoveDatabase database = PandaLoveDatabase.getInstance(application);
        dao = database.Dao();
        allHabits = dao.getAllHabits();
    }

    // method insert the data to database
    public void insert(PandaLoveModal model) {
        new InsertHabitAsyncTask(dao).execute(model);
    }

    // method update data in database
    public void update(PandaLoveModal model) {
        new UpdateHabitAsyncTask(dao).execute(model);
    }

    // method delete the data in database
    public void delete(PandaLoveModal model) {
        new DeleteHabitAsyncTask(dao).execute(model);
    }

    // method to delete all the habits in database
    public void deleteAllHabits() {
        new DeleteAllHabitsAsyncTask(dao).execute();
    }

    // read all the habits
    public LiveData<List<PandaLoveModal>> getAllHabits() {
        return allHabits;
    }

    // creating an async task method to insert new habit
    private static class InsertHabitAsyncTask extends AsyncTask<PandaLoveModal, Void, Void> {
        private PandaLoveDao dao;

        private InsertHabitAsyncTask(PandaLoveDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(PandaLoveModal... model) {
            //insert modal in dao
            dao.insert(model[0]);
            return null;
        }
    }

    // create an async task method to update habit
    private static class UpdateHabitAsyncTask extends AsyncTask<PandaLoveModal, Void, Void> {
        private PandaLoveDao dao;

        private UpdateHabitAsyncTask(PandaLoveDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(PandaLoveModal... models) {
            // update modal in dao
            dao.update(models[0]);
            return null;
        }
    }

    // create an async task method to delete habit
    private static class DeleteHabitAsyncTask extends AsyncTask<PandaLoveModal, Void, Void> {
        private PandaLoveDao dao;

        private DeleteHabitAsyncTask(PandaLoveDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(PandaLoveModal... models) {
            // delete modal in dao
            dao.delete(models[0]);
            return null;
        }
    }

    // create an async task method to delete all habits
    private static class DeleteAllHabitsAsyncTask extends AsyncTask<Void, Void, Void> {
        private PandaLoveDao dao;
        private DeleteAllHabitsAsyncTask(PandaLoveDao dao) {
            this.dao = dao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            // delete all habits in dao
            dao.deleteAllHabits();
            return null;
        }
    }
}