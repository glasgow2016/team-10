package com.leokomarov.groute.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkStuff {

    public static final String BASE_URL = "http://api.myservice.com/";

    Retrofit retrofit;
    EndpointInterface apiService;

    public NetworkStuff() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public void createApiService(){
        apiService = retrofit.create(EndpointInterface.class);
    }


}
