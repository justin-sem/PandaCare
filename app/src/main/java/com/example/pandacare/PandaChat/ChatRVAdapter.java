package com.example.pandacare.PandaChat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pandacare.R;

import java.util.ArrayList;

public class ChatRVAdapter extends RecyclerView.Adapter {

    // variable for our array list and context.
    private ArrayList<ChatModal> messageModalArrayList;
    private Context context;

    // create constructor class
    public ChatRVAdapter(ArrayList<ChatModal> messageModalArrayList, Context context) {
        this.messageModalArrayList = messageModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        // here is to switch our layout type along with view holder
        switch (viewType) {
            case 0:
                // below line we are inflating user message layout
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_chat, parent, false);
                return new UserViewHolder(view);
            case 1:
                // below line are inflating bot message layout
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pandabot_chat, parent, false);
                return new BotViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        // this method is use to set data to our layout file
        ChatModal modal = messageModalArrayList.get(position);
        switch (modal.getSender()) {
            case "user":
                // below line is to set the text to our text view of user layout
                ((UserViewHolder) holder).userTV.setText(modal.getMessage());
                break;
            case "bot":
                // below line is to set the text to our text view of bot layout
                ((BotViewHolder) holder).botTV.setText(modal.getMessage());
                break;
        }
    }

    @Override
    public int getItemCount() {
        // return the size of array list
        return messageModalArrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        // set position
        switch (messageModalArrayList.get(position).getSender()) {
            case "user":
                return 0;
            case "bot":
                return 1;
            default:
                return -1;
        }
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {

        // creating variable for the text view
        TextView userTV;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            // initialising with id
            userTV = itemView.findViewById(R.id.idTVUser);
        }
    }

    public static class BotViewHolder extends RecyclerView.ViewHolder {

        // creating variable for the text view
        TextView botTV;

        public BotViewHolder(@NonNull View itemView) {
            super(itemView);
            // initialising with id
            botTV = itemView.findViewById(R.id.idTVBot);
        }
    }
}
