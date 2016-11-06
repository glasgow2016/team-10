package com.leokomarov.groute.network;

import com.leokomarov.groute.db.Client;
import com.leokomarov.groute.db.Fellowship;
import com.leokomarov.groute.db.Guardian;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface EndpointInterface {

    // Request method and URL specified in the annotation
    // Callback for the parsed response is the last parameter

    //clients
    @GET("/client/create")
    Call<Client> createClient(@QueryMap Map<String, Object> options);

    @GET("/client/login")
    void loginClient(@Query("client_id") int client_id);

    //guardians
    @GET("/guardian/create")
    Call<Void> createGuardian(@QueryMap Map<String, Object> options);

    @GET("/guardian/auth")
    void authGuardian(@Query("client_id") int client_id);

    @GET("/guardian/get")
    Call<Guardian> getGuardian(@Query("phone_num") int phone_num);

    @GET("/guardian/login")
    void loginGuardian(@Query("phone_num") int phone_num);

    //fellowships
    @GET("/fellowship/create")
    Call<Fellowship> createFellowship(@QueryMap Map<String, Object> options);

    @GET("/fellowship/get")
    Call<Fellowship> getFellowship(@Query("fellowship_id") int fellowship_id);

    @GET("/fellowship/getFirst")
    Call<Fellowship> getFirstFellowship();

    @GET("/fellowship/delete")
    void deleteFellowship(@Query("fellowship_id") int fellowship_id);

    @GET("/fellowship/join")
    Call<Boolean> joinFellowship(@QueryMap Map<String, Object> options);

    @GET("/fellowship/addJourney")
    void addJourneyToFellowship(@QueryMap Map<String, Object> options);

    @GET("/fellowship/deleteJourney")
    void deleteJourneyFromFellowship(@QueryMap Map<String, Object> options);

    //journeys
    @GET("/journey/create")
    Call<Integer> createJourney(@QueryMap Map<String, Object> options);

    @GET("/journey/start")
    void startJourney(@QueryMap Map<String, Object> options);

    @GET("/journey/end")
    void endJourney(@QueryMap Map<String, Object> options);
}
