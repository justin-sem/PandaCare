package com.example.pandacare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

public class PandaCareHomePage extends AppCompatActivity {

    // initialising variables for widgets in xml file
    private Button healthButton;
    private Button mentalHealthButton;
    ImageView pandaIcon;
    ImageView menuIcon;
    TextView textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.panda_care_home_page);

        // initialising view
        pandaIcon = findViewById(R.id.mainIcon);
        menuIcon = findViewById(R.id.menuIcon);
        textView3 = findViewById(R.id.textView3);

        // get the name from the database and display on the home screen
        String name = getIntent().getStringExtra("name");
        textView3.setText(name);

        // add menu on click listener
        menuIcon.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                displayMenu(v);
            }
        });

        healthButton=  findViewById(R.id.healthButton);
        healthButton.setOnClickListener(new View.OnClickListener() {
              @Override
        public void onClick (View view){
                  openHealthPage();

        }
          });

           mentalHealthButton = findViewById(R.id.mentalHealthButton);
           mentalHealthButton.setOnClickListener(new View.OnClickListener(){
               @Override
               public void onClick (View v){
                    openMentalHealthPage();
               }

           });

    }

    // menu function
    public void displayMenu(View v){
        PopupMenu menu = new PopupMenu(PandaCareHomePage.this, v);
        menu.getMenuInflater().inflate(R.menu.menu,menu.getMenu());
        menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.about:
                        //implement function later here
                        Log.i("Item selected","About Me");
                        return true;
                    case R.id.settings:
                        //implement function later here
                        Log.i("item selected","Settings");
                        return true;
                    default:
                        return false;

                }
            }
        });
        menu.show();
    }

    // create intent to open panda health page
    public void openHealthPage(){
        Intent healthIntent = new Intent(this, PandaCareHealthPage.class);
        startActivity(healthIntent);

    }

    // create intent to open panda mental health page
    public void openMentalHealthPage(){
        Intent mentalHealthIntent = new Intent(this, PandaCareMentalHealthPage.class);
        startActivity(mentalHealthIntent);

    }


}

