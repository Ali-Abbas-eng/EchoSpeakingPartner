package com.example.client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.client.Logic.Accounts.User;

public class MainActivity extends AppCompatActivity {
    TextView welcomeMessage;
    Button logoutButton;

    public void goToLoginActivity(){
        Intent loginIntent = new Intent(getApplicationContext(), Login.class);
        startActivity(loginIntent);
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle passedInformation = getIntent().getExtras();
        User user = (User) passedInformation.getSerializable("LOGIN_USER_INFO");
        welcomeMessage = findViewById(R.id.hello_world_text_view);
        welcomeMessage.setText("Hello World " + user);
        logoutButton = findViewById(R.id.logout_button);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToLoginActivity();
            }
        });


    }
}