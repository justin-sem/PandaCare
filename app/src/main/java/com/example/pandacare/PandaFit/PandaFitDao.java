package com.example.pandacare.PandaFit;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

// Add annotation to PandaFitDao class
@androidx.room.Dao
public interface PandaFitDao {

    //insert data to database
    @Insert
    void insert(PandaFitModal model);

    //update data in database
    @Update
    void update(PandaFitModal model);

    // delete specific schedule in database
    @Delete
    void delete(PandaFitModal model);

    //making query to delete all schedule from database
    @Query("DELETE FROM PandaFitScheduleTable")
    void deleteAllSchedules();

    // read all the schedule from our database
    // Here is according to schedule id in ascending order
    @Query("SELECT * FROM PandaFitScheduleTable ORDER BY id ASC")
    LiveData<List<PandaFitModal>> getAllSchedules();
}