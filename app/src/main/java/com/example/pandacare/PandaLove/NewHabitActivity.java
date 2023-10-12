package com.example.pandacare.PandaLove;

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

public class NewHabitActivity extends AppCompatActivity {

    private EditText badHabitET;
    private Button saveHabit;

    // initialising constant string variables for habit
    public static final String EXTRA_ID = "com.gtappdevelopers.gfgroomdatabase.EXTRA_ID";
    public static final String EXTRA_BAD_HABIT = "com.gtappdevelopers.gfgroomdatabase.EXTRA_BAD_HABIT";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_habit);

        // initialising variables for each view.
        badHabitET = findViewById(R.id.badHabitET);
        saveHabit = findViewById(R.id.saveHabit);

        // getting intent in the below code
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            // setting values to the edit text fields
            badHabitET.setText(intent.getStringExtra(EXTRA_BAD_HABIT));
        }

        saveHabit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // getting text from ET and validating if the text are empty or not
                String badHabit = badHabitET.getText().toString();
                if (badHabit.isEmpty()) {
                    Toast.makeText(NewHabitActivity.this, "Please enter the valid habit.", Toast.LENGTH_LONG).show();
                    return;
                }
                // method to save schedule
                saveHabit(badHabit);

                // make the keyboard close down after sending the message
                InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                manager.hideSoftInputFromWindow(badHabitET.getWindowToken(),0);
            }
        });
    }

    private void saveHabit(String badHabit) {
        // inside saveHabit are passing all the data via an intent
        Intent data = new Intent();

        // passing habit details here
        data.putExtra(EXTRA_BAD_HABIT, badHabit);
        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            //passing id
            data.putExtra(EXTRA_ID, id);
        }

        // results are set as data
        setResult(RESULT_OK, data);

        // toast msg will be displayed after adding the data successfully
        Toast.makeText(this, "Bad habit has successfully been saved! ", Toast.LENGTH_LONG).show();
    }
}