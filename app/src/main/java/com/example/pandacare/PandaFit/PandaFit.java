/**  Here PandaFit is implemented with Room Database based on
 https://www.geeksforgeeks.org/how-to-perform-crud-operations-in-room-database-in-android/

**/
package com.example.pandacare.PandaFit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pandacare.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class PandaFit extends AppCompatActivity {

    // initialising variables
    private ViewModal viewmodal;
    private RecyclerView scheduleRV;
    private static final int ADD_SCHEDULE_REQUEST = 1;
    private static final int EDIT_SCHEDULE_REQUEST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panda_fit);

        // initialising variables for scheduleListView and fab
        scheduleRV = findViewById(R.id.scheduleListView);
        FloatingActionButton fab = findViewById(R.id.scheduleAdd);

        // on click listener for fab
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // starting a new activity which allow user to add a new schedule
                Intent intent = new Intent(PandaFit.this, NewFitScheduleActivity.class);
                startActivityForResult(intent, ADD_SCHEDULE_REQUEST);
            }
        });

        // setting layout manager to the adapter class
        scheduleRV.setLayoutManager(new LinearLayoutManager(this));
        scheduleRV.setHasFixedSize(true);

        // initialising adapter
        final ScheduleRVAdapter adapter = new ScheduleRVAdapter();

        // set adapter class for the schedule list view
        scheduleRV.setAdapter(adapter);

        // passing a data from view modal
        viewmodal = ViewModelProviders.of(this).get(ViewModal.class);

        // here all schedules are received from view modal
        viewmodal.getAllCourses().observe(this, new Observer<List<PandaFitModal>>() {
            @Override
            public void onChanged(List<PandaFitModal> models) {
                // when there is data change in model we are adding that list to our adapter class
                adapter.submitList(models);
            }
        });
        // method here is user able to swipe and delete item of list view
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                viewmodal.delete(adapter.getScheduleAt(viewHolder.getAdapterPosition()));
                Toast.makeText(PandaFit.this, "Schedule deleted", Toast.LENGTH_LONG).show();
            }
        }).attachToRecyclerView(scheduleRV);

        // set item click listener for the items of recycler view
        adapter.setOnItemClickListener(new ScheduleRVAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(PandaFitModal model) {
                // opening a new activity here and passing the relevant data to the activity
                Intent intent = new Intent(PandaFit.this, NewFitScheduleActivity.class);
                intent.putExtra(NewFitScheduleActivity.EXTRA_ID, model.getId());
                intent.putExtra(NewFitScheduleActivity.EXTRA_SCHEDULE_DATE, model.getScheduleDate());
                intent.putExtra(NewFitScheduleActivity.EXTRA_SCHEDULE_TIME, model.getScheduleTime());
                intent.putExtra(NewFitScheduleActivity.EXTRA_SCHEDULE_PLAN, model.getSchedulePlan());

                // below line is to start a new activity and add a edit schedule constant
                startActivityForResult(intent, EDIT_SCHEDULE_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_SCHEDULE_REQUEST && resultCode == RESULT_OK) {
            String scheduleDate = data.getStringExtra(NewFitScheduleActivity.EXTRA_SCHEDULE_DATE);
            String scheduleTime = data.getStringExtra(NewFitScheduleActivity.EXTRA_SCHEDULE_TIME);
            String schedulePlan = data.getStringExtra(NewFitScheduleActivity.EXTRA_SCHEDULE_PLAN);
            PandaFitModal model = new PandaFitModal(scheduleDate, scheduleTime, schedulePlan);
            viewmodal.insert(model);
            Toast.makeText(this, "Schedule saved", Toast.LENGTH_LONG).show();
;
        } else if (requestCode == EDIT_SCHEDULE_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(NewFitScheduleActivity.EXTRA_ID, -1);
            if (id == -1) {
                Toast.makeText(this, "Sorry, schedule can't be updated", Toast.LENGTH_LONG).show();
                return;
            }
            String scheduleDate = data.getStringExtra(NewFitScheduleActivity.EXTRA_SCHEDULE_DATE);
            String scheduleTime = data.getStringExtra(NewFitScheduleActivity.EXTRA_SCHEDULE_TIME);
            String schedulePlan = data.getStringExtra(NewFitScheduleActivity.EXTRA_SCHEDULE_PLAN);
            PandaFitModal model = new PandaFitModal(scheduleDate, scheduleTime, schedulePlan);
            model.setId(id);
            viewmodal.update(model);
            Toast.makeText(this, "Schedule updated", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Schedule not saved", Toast.LENGTH_LONG).show();
        }
    }
}
