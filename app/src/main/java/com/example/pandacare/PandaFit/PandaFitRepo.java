package com.example.pandacare.PandaFit;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class PandaFitRepo {

    private PandaFitDao dao;
    private LiveData<List<PandaFitModal>> allSchedules;

    // creating a constructor for the variables
    public PandaFitRepo(Application application) {
        PandaFitDatabase database = PandaFitDatabase.getInstance(application);
        dao = database.Dao();
        allSchedules = dao.getAllSchedules();
    }

    // method insert the data to database
    public void insert(PandaFitModal model) {
        new InsertScheduleAsyncTask(dao).execute(model);
    }

    // method update data in database
    public void update(PandaFitModal model) {
        new UpdateScheduleAsyncTask(dao).execute(model);
    }

    // method delete the data in database
    public void delete(PandaFitModal model) {
        new DeleteScheduleAsyncTask(dao).execute(model);
    }

    // method to delete all the schedule in database
    public void deleteAllSchedules() {
        new DeleteAllSchedulesAsyncTask(dao).execute();
    }

    // read all the schedules
    public LiveData<List<PandaFitModal>> getAllSchedules() {
        return allSchedules;
    }

    // creating an async task method to insert new schedule
    private static class InsertScheduleAsyncTask extends AsyncTask<PandaFitModal, Void, Void> {
        private PandaFitDao dao;

        private InsertScheduleAsyncTask(PandaFitDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(PandaFitModal... model) {
            // insert modal in dao
            dao.insert(model[0]);
            return null;
        }
    }

    // create an async task method to update schedule
    private static class UpdateScheduleAsyncTask extends AsyncTask<PandaFitModal, Void, Void> {
        private PandaFitDao dao;

        private UpdateScheduleAsyncTask(PandaFitDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(PandaFitModal... models) {
            // update modal in dao
            dao.update(models[0]);
            return null;
        }
    }

    // create an async task method to delete schedule
    private static class DeleteScheduleAsyncTask extends AsyncTask<PandaFitModal, Void, Void> {
        private PandaFitDao dao;

        private DeleteScheduleAsyncTask(PandaFitDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(PandaFitModal... models) {
            // delete modal in dao
            dao.delete(models[0]);
            return null;
        }
    }

    // create an async task method to delete all schedules
    private static class DeleteAllSchedulesAsyncTask extends AsyncTask<Void, Void, Void> {
        private PandaFitDao dao;
        private DeleteAllSchedulesAsyncTask(PandaFitDao dao) {
            this.dao = dao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            // delete all schedules in dao
            dao.deleteAllSchedules();
            return null;
        }
    }
}
