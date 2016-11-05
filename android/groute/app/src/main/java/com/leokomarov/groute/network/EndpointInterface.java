package com.leokomarov.groute.network;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface EndpointInterface {

    // Request method and URL specified in the annotation
    // Callback for the parsed response is the last parameter

    @GET("users/{username}")
    Call<User> getUser(@Path("username") String username);

    @GET("group/{id}/users")
    Call<List<User>> groupList(@Path("id") int groupId, @Query("sort") String sort);

}
