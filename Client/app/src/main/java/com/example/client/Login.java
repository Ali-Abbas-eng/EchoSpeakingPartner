package com.example.client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.client.Connection.Accounts;
import com.example.client.Connection.Authenticator;
import com.example.client.Logic.Accounts.LoginData;
import com.example.client.Logic.Accounts.LoginResult;
import com.example.client.Logic.Accounts.User;
import com.example.client.Logic.DataValidator;
import com.google.android.material.textfield.TextInputEditText;

import java.util.concurrent.atomic.AtomicReference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    TextInputEditText usernameInputTextField, passwordTextInputField;
    Button loginButton;
    ProgressBar loginProgressBar;

    TextView loginTextView;

    DataValidator dataValidator;
    Authenticator authenticator;
    public void goToNextActivity(User user){

        Intent mainActivityIntent = new Intent(getApplicationContext(), MainActivity.class);
        Bundle intentInformation = new Bundle();
        intentInformation.putSerializable("LOGIN_USER_INFO", user);
        mainActivityIntent.putExtras(intentInformation);
        startActivity(mainActivityIntent);
        finish();

    }

    public void login(String username, String password){
        Accounts accountsAPI = authenticator.getApiClient().create(Accounts.class);
        Call<LoginResult> call = accountsAPI.login(new LoginData(username, password));
        call.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                if (response.isSuccessful()){
                    assert response.body() != null;
                    goToNextActivity(new User(response.body()));
                }
                else {
                    System.out.println("The Call Failed!!!");
                }
            }
            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                t.printStackTrace();
            }
        });
        AtomicReference<LoginResult> resultAtomicReference = new AtomicReference<>();
        try{
            authenticator.login(new LoginData(username, password), resultAtomicReference);
            if (resultAtomicReference.get() != null){
                goToNextActivity(new User(resultAtomicReference.get()));
            }
            else {
                Toast.makeText(Login.this, "The value of the connection result is " + resultAtomicReference.get(), Toast.LENGTH_SHORT).show();
            }
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dataValidator = new DataValidator(false);
        usernameInputTextField = findViewById(R.id.username_input_field);
        passwordTextInputField = findViewById(R.id.password_input_field);
        loginProgressBar = findViewById(R.id.registration_progress_bar);
        loginTextView = findViewById(R.id.login_text_view);
        authenticator = new Authenticator();

        loginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registrationIntent = new Intent(getApplicationContext(), Registration.class);
                startActivity(registrationIntent);
                finish();
            }
        });

        loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){
                loginProgressBar.setVisibility(View.VISIBLE);
                String username, password;
                username = String.valueOf(usernameInputTextField.getText());
                password = String.valueOf(passwordTextInputField.getText());
                String validationMessage = dataValidator.validate(username, password);

                if (validationMessage.equals("")){
                    login(username, password);
                }
                else {
                    Toast.makeText(Login.this, validationMessage, Toast.LENGTH_SHORT).show();
                }
                loginProgressBar.setVisibility(View.GONE);

            }

        });
    }
}
