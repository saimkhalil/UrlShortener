package com.urlshortener.app.Client;

import retrofit2.http.GET;

public interface AuthClient
{

    @GET(value = "/fetchUserById")
    Call<Res>
}
