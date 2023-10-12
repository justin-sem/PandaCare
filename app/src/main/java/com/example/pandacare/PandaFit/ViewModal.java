package com.example.pandacare.PandaFit;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ViewModal extends AndroidViewModel {

    private PandaFitRepo repository;
    private LiveData<List<PandaFitModal>> allSchedules;

    // constructor for view modal
    public ViewModal(@NonNull Application application) {
        super(application);
        repository = new PandaFitRepo(application);
        allSchedules = repository.getAllSchedules();
    }

    // insert the data to repository
    public void insert(PandaFitModal model) {
        repository.insert(model);
    }

    // update data in repository
    public void update(PandaFitModal model) {
        repository.update(model);
    }

    // delete the data in repository
    public void delete(PandaFitModal model) {
        repository.delete(model);
    }

    // delete all the schedules in the list
    public void deleteAllCourses() {
        repository.deleteAllSchedules();
    }

    //get all the schedules in the list
    public LiveData<List<PandaFitModal>> getAllCourses() {
        return allSchedules;
    }
}
