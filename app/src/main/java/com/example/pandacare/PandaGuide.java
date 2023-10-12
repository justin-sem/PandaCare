package com.example.pandacare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class PandaGuide extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panda_guide);
    }

    // create implicit intent to open website
    public void guideOneButtonClicked(View view){
        Intent intentOne = new Intent(Intent.ACTION_VIEW);
        intentOne.setData(Uri.parse("https://www.who.int/campaigns/connecting-the-world-to-combat-coronavirus/healthyathome"));
        startActivity(intentOne);
    }

    // create implicit intent to open website
    public void guideTwoButtonClicked(View view){
        Intent intentOne = new Intent(Intent.ACTION_VIEW);
        intentOne.setData(Uri.parse("https://mmha.org.my/"));
        startActivity(intentOne);
    }

    // create implicit intent to open website
    public void guideThreeButtonClicked(View view){
        Intent intentOne = new Intent(Intent.ACTION_VIEW);
        intentOne.setData(Uri.parse("https://www.befrienders.org.my/"));
        startActivity(intentOne);
    }
}