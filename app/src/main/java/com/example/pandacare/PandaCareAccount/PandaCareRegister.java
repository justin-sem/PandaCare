package com.example.pandacare.PandaCareAccount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pandacare.R;

public class PandaCareRegister extends AppCompatActivity {

    private EditText usernameRegisterInput, passwordRegisterInput, nameInput;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panda_care_register);

        // initialising the edit text view and button
        usernameRegisterInput = findViewById(R.id.usernameRegisterInput);
        passwordRegisterInput = findViewById(R.id.passwordRegisterInput);
        nameInput = findViewById(R.id.nameInput);
        registerButton= findViewById(R.id.registerButton);

        // add on click listener on registerButton
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PandaUserEntity pandaUserEntity = new PandaUserEntity();
                pandaUserEntity.setUsername(usernameRegisterInput.getText().toString());
                pandaUserEntity.setPassword(passwordRegisterInput.getText().toString());
                pandaUserEntity.setName(nameInput.getText().toString());

                // making sure the user input is not empty
                if (pandaUserEntity.getName().isEmpty() || pandaUserEntity.getPassword().isEmpty() || pandaUserEntity.getUsername().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please fill up all the information !", Toast.LENGTH_SHORT).show();
                } else{
                    // initialise user database and dao
                    PandaUserDatabase pandaUserDatabase = PandaUserDatabase.getUserDB(getApplicationContext());
                    PandaUserDao pandaUserDao = pandaUserDatabase.userDao();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            // register the user into database
                            pandaUserDao.registerUser(pandaUserEntity);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    // once register successful, bring user back to log in page
                                    Toast.makeText(getApplicationContext(),"Registration Successful!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(PandaCareRegister.this, PandaCareLogInPage.class);
                                    startActivity(intent);
                                }
                            });

                        }
                    }).start();

                }

            }
        });
    }





}