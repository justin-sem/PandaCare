package com.example.pandacare.PandaCareAccount;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pandacare.NotificationBroadcast;
import com.example.pandacare.PandaCareHomePage;
import com.example.pandacare.R;

import java.util.Calendar;

public class PandaCareLogInPage extends AppCompatActivity {

    private EditText usernameInput;
    private EditText passwordInput;
    private Button logInButton;
    private TextView textView11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panda_care_log_in_page);

        // initialising view for the edit text and button
        usernameInput = findViewById(R.id.usernameInput);
        passwordInput = findViewById(R.id.passwordInput);
        logInButton = findViewById(R.id.logInButton);
        textView11 = findViewById(R.id.textView11);

        // on click listener when log in button is pressed
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pandaUsername = usernameInput.getText().toString();
                String pandaPassword = passwordInput.getText().toString();

                // making sure the user input is not empty
                if (pandaUsername.isEmpty() || pandaPassword.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter a valid username or password!", Toast.LENGTH_SHORT).show();
                } else {
                    // initialise database by perform query
                    PandaUserDatabase pandaUserDatabase = PandaUserDatabase.getUserDB(getApplicationContext());
                    PandaUserDao pandaUserDao = pandaUserDatabase.userDao();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            //calling log in method
                            PandaUserEntity pandaUserEntity = pandaUserDao.login(pandaUsername, pandaPassword);
                            if (pandaUserEntity == null) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), "Invalid Credentials!", Toast.LENGTH_SHORT).show();

                                    }
                                });
                            } else {
                                // once log in success, open panda home page
                                String name = pandaUserEntity.name;
                                startActivity(new Intent(PandaCareLogInPage.this, PandaCareHomePage.class).putExtra("name", name));


                            }
                        }
                    }).start();
                }
            }
        });
        // this is the text view which bring user to registration page
        textView11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PandaCareLogInPage.this, PandaCareRegister.class));
            }
        });

        // create notification channel here so when PandaCare will send notification on 10:00 am everyday
        // to remind the user to stay away with his/her bad habit
        NotificationChannel();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 10);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND, 00);

        // making sure the notification only send once a day
        if(Calendar.getInstance().after(calendar)){
            calendar.add(Calendar.DAY_OF_MONTH,1);
        }

        Intent intent = new Intent(PandaCareLogInPage.this, NotificationBroadcast.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // initialising alarm manager
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
        }

    }
    // setting and create channel for notification
    private void NotificationChannel() {

        // if sdk version higher than oreo, O
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "PandaCare";
            String description = "PandaCare Application";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("Notification", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

        }

    }
}

