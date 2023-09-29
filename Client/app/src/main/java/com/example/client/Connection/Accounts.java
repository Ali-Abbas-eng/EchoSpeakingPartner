package com.example.client.Connection;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Headers;
import com.example.client.Constants.Endpoints;
import com.example.client.Logic.Accounts.LoginData;
import com.example.client.Logic.Accounts.LoginResult;
import com.example.client.Logic.Accounts.User;

public interface Accounts {

    @Headers({"Accept: application/json"})
    @POST(Endpoints.LOGIN_ENDPOINT)
    Call<LoginResult> login(@Body LoginData body);

    @POST(Endpoints.LOGOUT_ENDPOINT)
    Call<User> logout();
}


