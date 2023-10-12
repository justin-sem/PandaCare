/**  // Here PandaLove is implemented with Room Database based on
 https://www.geeksforgeeks.org/how-to-perform-crud-operations-in-room-database-in-android/

 **/

package com.example.pandacare.PandaLove;

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

public class PandaLove extends AppCompatActivity {

    // initialising variables for widgets in xml file
    private loveViewModal loveViewModal;
    private RecyclerView habitRV;
    private static final int ADD_HABIT_REQUEST = 1;
    private static final int EDIT_HABIT_REQUEST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panda_love);

        // initialising variables for habitListView and fab
        habitRV = findViewById(R.id.habitListView);
        FloatingActionButton fab = findViewById(R.id.habitAdd);

        // on click listener for fab
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // starting a new activity which allow user to add a new habit
                Intent intent = new Intent(PandaLove.this, NewHabitActivity.class);
                startActivityForResult(intent, ADD_HABIT_REQUEST);
            }
        });

        // setting layout manager to our adapter class
        habitRV.setLayoutManager(new LinearLayoutManager(this));
        habitRV.setHasFixedSize(true);

        // initialising adapter
        final HabitRVAdapter adapter = new HabitRVAdapter();

        // set adapter class for the habit list view
        habitRV.setAdapter(adapter);

        // passing a data from view modal
        loveViewModal = ViewModelProviders.of(this).get(loveViewModal.class);

        // here all habits are received from view modal
        loveViewModal.getAllHabits().observe(this, new Observer<List<PandaLoveModal>>() {
            @Override
            public void onChanged(List<PandaLoveModal> models) {
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
                loveViewModal.delete(adapter.getScheduleAt(viewHolder.getAdapterPosition()));
                Toast.makeText(PandaLove.this, "Habit deleted", Toast.LENGTH_LONG).show();
            }
        }).attachToRecyclerView(habitRV);

        // set item click listener for our item of recycler view
        adapter.setOnItemClickListener(new HabitRVAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(PandaLoveModal model) {
                // opening a new activity here and passing the relevant data to the activity
                Intent intent = new Intent(PandaLove.this, NewHabitActivity.class);
                intent.putExtra(NewHabitActivity.EXTRA_ID, model.getId());
                intent.putExtra(NewHabitActivity.EXTRA_BAD_HABIT, model.getBadHabit());

                // below line is to start a new activity and add a edit habit constant
                startActivityForResult(intent, EDIT_HABIT_REQUEST);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_HABIT_REQUEST && resultCode == RESULT_OK) {
            String badHabit = data.getStringExtra(NewHabitActivity.EXTRA_BAD_HABIT);

            PandaLoveModal model = new PandaLoveModal(badHabit);
            loveViewModal.insert(model);
            Toast.makeText(this, "Habit saved", Toast.LENGTH_LONG).show();
            ;
        } else if (requestCode == EDIT_HABIT_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(NewHabitActivity.EXTRA_ID, -1);
            if (id == -1) {
                Toast.makeText(this, "Sorry, habit can't be updated", Toast.LENGTH_LONG).show();
                return;
            }
            String badHabit = data.getStringExtra(NewHabitActivity.EXTRA_BAD_HABIT);
            PandaLoveModal model = new PandaLoveModal(badHabit);
            model.setId(id);
            loveViewModal.update(model);
            Toast.makeText(this, "Habit updated", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Habit not saved", Toast.LENGTH_LONG).show();
        }
    }


}