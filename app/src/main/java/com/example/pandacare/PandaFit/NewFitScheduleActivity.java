package com.example.pandacare.PandaFit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pandacare.R;

public class NewFitScheduleActivity extends AppCompatActivity {

    private EditText scheduleDateET;
    private EditText scheduleTimeET ;
    private EditText schedulePlanET;
    private Button saveSchedule;

    // initialising constant string variables for our schedule date, time and plan
    public static final String EXTRA_ID = "com.gtappdevelopers.gfgroomdatabase.EXTRA_ID";
    public static final String EXTRA_SCHEDULE_DATE = "com.gtappdevelopers.gfgroomdatabase.EXTRA_SCHEDULE_DATE";
    public static final String EXTRA_SCHEDULE_TIME = "com.gtappdevelopers.gfgroomdatabase.EXTRA_SCHEDULE_TIME";
    public static final String EXTRA_SCHEDULE_PLAN = "com.gtappdevelopers.gfgroomdatabase.EXTRA_SCHEDULE_PLAN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_fit_schedule);

        // initialising variables for each view
        scheduleDateET = findViewById(R.id.scheduleDateET);
        scheduleTimeET = findViewById(R.id.scheduleTimeET);
        schedulePlanET= findViewById(R.id.schedulePlanET);
        saveSchedule = findViewById(R.id.saveSchedule);

        // getting intent here
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            //setting values to our edit text fields.
            scheduleDateET.setText(intent.getStringExtra(EXTRA_SCHEDULE_DATE));
            scheduleTimeET.setText(intent.getStringExtra(EXTRA_SCHEDULE_TIME));
            schedulePlanET.setText(intent.getStringExtra(EXTRA_SCHEDULE_PLAN));
        }

        saveSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // getting text from edit text and validating if the text are empty or not
                String scheduleDate = scheduleDateET.getText().toString();
                String scheduleTime = scheduleTimeET.getText().toString();
                String schedulePlan = schedulePlanET.getText().toString();
                if (scheduleDate.isEmpty() || scheduleTime.isEmpty() || schedulePlan.isEmpty()) {
                    Toast.makeText(NewFitScheduleActivity.this, "Please enter the valid schedule details.", Toast.LENGTH_LONG).show();
                    return;
                }
                // method to save schedule
                saveSchedule(scheduleDate, scheduleTime, schedulePlan);

                // make the keyboard close down after sending the message
                InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                manager.hideSoftInputFromWindow(schedulePlanET.getWindowToken(),0);
            }
        });
    }

    private void saveSchedule(String scheduleDate, String scheduleTime, String schedulePlan) {
        // inside saveSchedule are passing all the data via an intent
        Intent data = new Intent();

        // passing schedule details here
        data.putExtra(EXTRA_SCHEDULE_DATE, scheduleDate);
        data.putExtra(EXTRA_SCHEDULE_TIME, scheduleTime);
        data.putExtra(EXTRA_SCHEDULE_PLAN, schedulePlan);
        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            // passing id
            data.putExtra(EXTRA_ID, id);
        }

        // results are set as data
        setResult(RESULT_OK, data);

        // toast msg will be displayed after adding the data successfully
        Toast.makeText(this, "Schedule has successfully been saved! ", Toast.LENGTH_LONG).show();
    }
}
