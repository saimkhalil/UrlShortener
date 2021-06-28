package com.urlshortener.app.Client;

import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Configuration
public class AuthClientConfig
{
    @Value("${auth.base.url}")
    private String BASE_URL;

    private Retrofit retrofit;

    public AuthClientConfig(Retrofit retrofit) {

        OkHttpClient client = new OkHttpClient();

        this.retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    @Bean
    public AuthClient getAuthClient()
    {
        return retrofit.create(AuthClient.class);
    }
}
