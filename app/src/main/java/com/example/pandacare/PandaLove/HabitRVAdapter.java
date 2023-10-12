package com.example.pandacare.PandaLove;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pandacare.R;

public class HabitRVAdapter extends ListAdapter<PandaLoveModal, HabitRVAdapter.ViewHolder> {

    private OnItemClickListener listener;
    HabitRVAdapter() {
        super(DIFF_CALLBACK);
    }

    // creating a call back for item of habit list view
    private static final DiffUtil.ItemCallback<PandaLoveModal> DIFF_CALLBACK = new DiffUtil.ItemCallback<PandaLoveModal>() {
        @Override
        public boolean areItemsTheSame(PandaLoveModal oldItem, PandaLoveModal newItem) {
            return oldItem.getId() == newItem.getId();
        }

        // here is to check the bad habit
        @Override
        public boolean areContentsTheSame(PandaLoveModal oldItem, PandaLoveModal newItem) {

            return oldItem.getBadHabit().equals(newItem.getBadHabit());
        }
    };

    // here use to inflate layout for each item of habit_rv
    @NonNull
    @Override
    public HabitRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.habit_rv, parent, false);
        return new HabitRVAdapter.ViewHolder(item);
    }

    // here is to set data to each item of habit recycler view
    @Override
    public void onBindViewHolder(@NonNull HabitRVAdapter.ViewHolder holder, int position) {
        PandaLoveModal model = getScheduleAt(position);
        holder.badHabitTV.setText(model.getBadHabit());
    }

    // method to get habit modal at a specific position
    public PandaLoveModal getScheduleAt(int position) {
        return getItem(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView badHabitTV;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initialising view
            badHabitTV = itemView.findViewById(R.id.badHabit);

            // adding on click listener for each item
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(PandaLoveModal model);
    }
    public void setOnItemClickListener(HabitRVAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }
}
