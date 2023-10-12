package com.example.pandacare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pandacare.PandaFit.PandaFit;
import com.example.pandacare.PandaLove.PandaLove;

public class PandaCareHealthPage extends AppCompatActivity {

    // initialise button in the health page
    private Button pandaDietButton;
    private Button pandaFitButton;
    private Button pandaLoveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panda_care_health_page);

        // initialise all the button views and set an on click listener

        pandaDietButton=  findViewById(R.id.pandaDietButton);
        pandaDietButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){
                openPandaDietPage();

            }
        });

        pandaFitButton = findViewById(R.id.pandaFitButton);
        pandaFitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                openPandaFitPage();
            }

        });
        pandaLoveButton = findViewById(R.id.pandaLoveButton);
        pandaLoveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                openPandaLovePage();
            }

        });

    }
    // create intent open panda diet page
    public void openPandaDietPage(){
        Intent pandaDietIntent = new Intent(this, PandaDiet.class);
        startActivity(pandaDietIntent);

    }

    // create intent open panda-fit page
    public void openPandaFitPage(){
        Intent pandaFitIntent = new Intent(this, PandaFit.class);
        startActivity(pandaFitIntent);

    }

    // create intent open panda love page
    public void openPandaLovePage(){
        Intent pandaLoveIntent = new Intent(this, PandaLove.class);
        startActivity(pandaLoveIntent);

    }
}
