package com.example.pandacare.PandaFit;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

// set schedule table name
@Entity(tableName = "PandaFitScheduleTable")
public class PandaFitModal {

    // set auto increment id for each schedule
    @PrimaryKey(autoGenerate = true)

    // variable for schedule id
    private int id;

    // variable for schedule date
    private String scheduleDate;

    // variable for schedule time
    private String scheduleTime;

    // variable for schedule plan
    private String schedulePlan;

    // creating constructor class
    public PandaFitModal(String scheduleDate, String scheduleTime, String schedulePlan) {
        this.scheduleDate = scheduleDate;
        this.scheduleTime = scheduleTime;
        this.schedulePlan = schedulePlan;
    }



    // Getter
    public int getId() {
        return id;
    }

    public String getScheduleDate() {
        return scheduleDate;
    }

    public String getScheduleTime() {return scheduleTime;
    }

    public String getSchedulePlan() {
        return schedulePlan;
    }


    // Setter
    public void setId(int id) {
        this.id = id;
    }

    public void setScheduleDate(String scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public void setScheduleTime(String scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public void setSchedulePlan(String schedulePlan) {
        this.schedulePlan = schedulePlan;
    }


}
