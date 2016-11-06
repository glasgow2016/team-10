package com.leokomarov.groute.network;

import com.leokomarov.groute.db.Fellowship;
import com.leokomarov.groute.db.Guardian;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

interface EndpointInterface {

    // Request method and URL specified in the annotation
    // Callback for the parsed response is the last parameter

    //clients
    @GET("client/create")
    Call<Integer> createClient(@QueryMap Map<String, String> options);

    @GET("client/login")
    void createClient(@Query("client_id") int client_id);

    //guardians
    @GET("guardian/create")
    void createGuardian(@QueryMap Map<String, String> options);

    @GET("guardian/auth")
    void authGuardian(@Query("client_id") int client_id);

    @GET("guardian/get")
    Call<Guardian> getGuardian(@Query("phone_num") int phone_num);

    @GET("guardian/login")
    void loginGuardian(@Query("phone_num") int phone_num);

    //fellowships
    @GET("fellowship/create")
    Call<Integer> createFellowship(@QueryMap Map<String, String> options);

    @GET("fellowship/get")
    Call<Fellowship> getFellowship(@Query("fellowship_id") int fellowship_id);

    @GET("fellowship/delete")
    void deleteFellowship(@Query("fellowship_id") int fellowship_id);

    @GET("fellowship/join")
    Call<Boolean> joinFellowship(@QueryMap Map<String, String> options);

    @GET("fellowship/addJourney")
    void addJourneyToFellowship(@QueryMap Map<String, String> options);

    @GET("fellowship/deleteJourney")
    void deleteJourneyFromFellowship(@QueryMap Map<String, String> options);

    //journeys
    @GET("journey/create")
    Call<Integer> createJourney(@QueryMap Map<String, String> options);

    @GET("journey/start")
    void startJourney(@QueryMap Map<String, String> options);

    @GET("journey/end")
    void endJourney(@QueryMap Map<String, String> options);
}
