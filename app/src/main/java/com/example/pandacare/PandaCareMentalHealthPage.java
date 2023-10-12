package com.example.pandacare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pandacare.PandaCalm.PandaCalm;
import com.example.pandacare.PandaChat.PandaChat;

public class PandaCareMentalHealthPage extends AppCompatActivity {

    // initialise button in the health page
    private Button pandaChatButton;
    private Button pandaCalmButton;
    private Button pandaGuideButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panda_care_mental_health_page);

        // initialise all the button views and set an on click listener

        pandaChatButton=  findViewById(R.id.pandaChatButton);
        pandaChatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){
                openPandaChatPage();

            }
        });

        pandaCalmButton=  findViewById(R.id.pandaCalmButton);
        pandaCalmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){
                openPandaCalmPage();

            }
        });

        pandaGuideButton=  findViewById(R.id.pandaGuideButton);
        pandaGuideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){
                openPandaGuidePage();

            }
        });
    }
    // create intent to open panda chat page
    public void openPandaChatPage(){
        Intent pandaChatIntent = new Intent(this, PandaChat.class);
        startActivity(pandaChatIntent);

    }
    // create intent to open panda calm page
    public void openPandaCalmPage(){
        Intent pandaCalmIntent = new Intent(this, PandaCalm.class);
        startActivity(pandaCalmIntent);

    }
    // create intent to open panda guide page
    public void openPandaGuidePage(){
        Intent pandaGuideIntent = new Intent(this, PandaGuide.class);
        startActivity(pandaGuideIntent);

    }
}