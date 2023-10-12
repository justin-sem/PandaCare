package com.example.pandacare.PandaLove;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

// set habit table name
@Entity(tableName = "PandaLoveHabitTable")
public class PandaLoveModal {

    // set auto increment id for each new habit
    @PrimaryKey(autoGenerate = true)

    // variable for habit id
    private int id;

    // variable for habit
    private String badHabit;

    // creating constructor class
    public PandaLoveModal(String badHabit) {
        this.badHabit = badHabit;

    }

    // Getter
    public int getId() {
        return id;
    }

    public String getBadHabit() {
        return badHabit;
    }


    // Setter
    public void setId(int id) {
        this.id = id;
    }

    public void setBadHabit(String badHabit) {
        this.badHabit = badHabit;
    }

}