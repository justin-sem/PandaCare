package com.example.pandacare.PandaFit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pandacare.R;

public class ScheduleRVAdapter extends ListAdapter<PandaFitModal, ScheduleRVAdapter.ViewHolder> {

    private OnItemClickListener listener;
    ScheduleRVAdapter() {
        super(DIFF_CALLBACK);
    }

    // creating a call back for item of schedule list view
    private static final DiffUtil.ItemCallback<PandaFitModal> DIFF_CALLBACK = new DiffUtil.ItemCallback<PandaFitModal>() {
        @Override
        public boolean areItemsTheSame(PandaFitModal oldItem, PandaFitModal newItem) {
            return oldItem.getId() == newItem.getId();
        }

        // here is to check the schedule date, time and plan
        @Override
        public boolean areContentsTheSame(PandaFitModal oldItem, PandaFitModal newItem) {

            return oldItem.getScheduleDate().equals(newItem.getScheduleDate()) &&
                    oldItem.getScheduleTime().equals(newItem.getScheduleTime()) &&
                    oldItem.getSchedulePlan().equals(newItem.getSchedulePlan());
        }
    };

    // here use to inflate layout for each item of schedule_rv
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.schedule_rv, parent, false);
        return new ViewHolder(item);
    }

    // here is to set data to each item of schedule recycler view
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PandaFitModal model = getScheduleAt(position);
        holder.scheduleDateTV.setText(model.getScheduleDate());
        holder.scheduleTimeTV.setText(model.getScheduleTime());
        holder.schedulePlanTV.setText(model.getSchedulePlan());
    }

    // method to get schedule modal at a specific position
    public PandaFitModal getScheduleAt(int position) {
        return getItem(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView scheduleDateTV, scheduleTimeTV, schedulePlanTV;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing each view
            scheduleDateTV = itemView.findViewById(R.id.scheduleDate);
            scheduleTimeTV = itemView.findViewById(R.id.scheduleTime);
            schedulePlanTV = itemView.findViewById(R.id.schedulePlan);

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
        void onItemClick(PandaFitModal model);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
