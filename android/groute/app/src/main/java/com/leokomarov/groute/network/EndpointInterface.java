package com.leokomarov.groute.network;

import com.leokomarov.groute.db.Fellowship;
import com.leokomarov.groute.db.Guardian;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
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

    //groups

    @GET("group/create")
    void createGroup(@QueryMap Map<String, String> options);

    @GET("group/delete")
    void deleteGroup(@Query("user_id") int user_id);

    @GET("group/display")
    Call<List<Fellowship>> displayGroup(@Query("user_id") int user_id);

    @GET("group/{id}/users")
    Call<List<User>> groupList(@Path("id") int groupId, @Query("sort") String sort);

}
