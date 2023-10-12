package com.example.pandacare.PandaLove;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class loveViewModal extends AndroidViewModel {

    private PandaLoveRepo repository;
    private LiveData<List<PandaLoveModal>> allHabits;

    // constructor for view modal
    public loveViewModal(@NonNull Application application) {
        super(application);
        repository = new PandaLoveRepo(application);
        allHabits = repository.getAllHabits();
    }

    // insert the data to repository
    public void insert(PandaLoveModal model) { repository.insert(model); }

    // update data in repository
    public void update(PandaLoveModal model) {
        repository.update(model);
    }

    // delete the data in repository
    public void delete(PandaLoveModal model) {
        repository.delete(model);
    }

    // delete all the habits in the list
    public void deleteAllHabits() {
        repository.deleteAllHabits();
    }

    // get all the habits in the list
    public LiveData<List<PandaLoveModal>> getAllHabits() {
        return allHabits;
    }
}
