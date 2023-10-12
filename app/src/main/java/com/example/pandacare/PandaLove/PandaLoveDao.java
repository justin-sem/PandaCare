package com.example.pandacare.PandaLove;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

// Add annotation to PandaLoveDao class
@androidx.room.Dao
public interface PandaLoveDao {

    //insert data to database
    @Insert
    void insert(PandaLoveModal model);

    //update data in database
    @Update
    void update(PandaLoveModal model);

    // delete specific habit in database
    @Delete
    void delete(PandaLoveModal model);

    //making query to delete all habits from database
    @Query("DELETE FROM PandaLoveHabitTable")
    void deleteAllHabits();

    // read all the habits from our database
    // Here is according to habit id in ascending order
    @Query("SELECT * FROM PandaLoveHabitTable ORDER BY id ASC")
    LiveData<List<PandaLoveModal>> getAllHabits();
}