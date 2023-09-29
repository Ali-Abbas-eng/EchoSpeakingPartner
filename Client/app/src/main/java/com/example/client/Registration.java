package com.example.client;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.client.Logic.DataValidator;
import com.google.android.material.textfield.TextInputEditText;


public class Registration extends AppCompatActivity {
    TextInputEditText emailTextInputField, passwordTextInputField;
    Button registrationButton;
    ProgressBar registrationProgressBar;

    TextView registrationTextView;

    DataValidator dataValidator;

    public void goToNextActivity(){
        Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(mainIntent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        dataValidator = new DataValidator(true);
        emailTextInputField = findViewById(R.id.username_input_field);
        passwordTextInputField = findViewById(R.id.password_input_field);
        registrationButton = findViewById(R.id.login_button);
        registrationProgressBar = findViewById(R.id.registration_progress_bar);
        registrationTextView = findViewById(R.id.registration_text_view);

        registrationTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(getApplicationContext(), Login.class);
                startActivity(loginIntent);
                finish();
            }
        });

        registrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrationProgressBar.setVisibility(View.VISIBLE);
                String email, password;
                email = String.valueOf(emailTextInputField.getText());
                password = String.valueOf(passwordTextInputField.getText());
                String validationMessage = dataValidator.validate(email, password);
                if (!validationMessage.equals("")){
                    Toast.makeText(Registration.this, validationMessage, Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}

