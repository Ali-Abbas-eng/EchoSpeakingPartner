package com.example.client.Connection;

import com.example.client.Constants.Endpoints;
import com.example.client.Logic.Accounts.LoginData;
import com.example.client.Logic.Accounts.LoginResult;
import com.example.client.Logic.Accounts.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Authenticator{
    Retrofit apiClient;
    User user;

    LoginResult result;

    public void setUser(User user){
        this.user = user;
    }

    public User getUser(){
        return this.user;
    }

    public Authenticator(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        this.apiClient = new Retrofit.Builder()
                .baseUrl(Endpoints.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }
    public Retrofit getApiClient(){
        return this.apiClient;
    }

    public void login(LoginData credentials, AtomicReference<LoginResult> resultAtomicReference) throws InterruptedException {
        Accounts accountsAPI = this.apiClient.create(Accounts.class);
        Call<LoginResult> call = accountsAPI.login(credentials);
        call.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                if (response.isSuccessful()){
                    resultAtomicReference.set(response.body());
                    System.out.println("Login request went through and the response was: " + resultAtomicReference.get());
                    System.out.println("Login request went through and the response was: " + resultAtomicReference.get());
                    System.out.println("Login request went through and the response was: " + resultAtomicReference.get());
                    System.out.println("Login request went through and the response was: " + resultAtomicReference.get());
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
        while (!call.isExecuted()){
            Thread.sleep(10);
        }

    }

}