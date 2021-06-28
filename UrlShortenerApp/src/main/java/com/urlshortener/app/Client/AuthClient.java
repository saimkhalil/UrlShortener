package com.urlshortener.app.Client;

import com.example.authserver.contracts.Response.ResponseModel;
import com.example.authserver.contracts.Response.UserResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AuthClient
{
    @GET(value = "/fetchUserById")
    Call<ResponseModel<UserResponse>> getUser(@Query("id") String id);
}
