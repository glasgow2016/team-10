package com.leokomarov.groute.dashboard;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler;
import com.leokomarov.groute.MainActivity;
import com.leokomarov.groute.R;
import com.leokomarov.groute.controllers.ButterKnifeController;
import com.leokomarov.groute.db.Fellowship;

import org.json.JSONObject;

import java.util.HashMap;

import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JoinFellowshipController extends ButterKnifeController {

    @OnClick(R.id.findAGroupButton)
    void findAGroupButtonClicked(){

        Call<Fellowship> firstFellowshipCall = MainActivity.networkStuff.apiService.getFirstFellowship();
        firstFellowshipCall.enqueue(new Callback<Fellowship>() {
            @Override
            public void onResponse(Call<Fellowship> call, Response<Fellowship> response) {
                int statusCode = response.code();

                Log.v("register-fell", String.format("response: %s", response.raw()));
                Log.v("register-fell", String.format("response.headers: %s", response.headers()));
                Log.v("register-fell", String.format("response.body: %s", response.body()));

                int fellowship_id = response.body().getId();
                MainActivity.client.setFellowship(fellowship_id);

                Log.v("register-fellfirst", String.format("statusCode: %d", statusCode));
                Log.v("register-fellfirst", String.format("fellowship_id: %d", fellowship_id));
                Log.v("register-fellfirst", String.format("client fellowship_id: %d", MainActivity.client.getFellowship()));
                Log.v("register-fellfirst", String.format("client_id: %d", MainActivity.client.getId()));


                HashMap<String, Object> queryOptions = new HashMap<>();

                queryOptions.put("client_id", MainActivity.client.getId());
                queryOptions.put("fellow_id", fellowship_id);

                Call<JSONObject> joinFellowshipCall = MainActivity.networkStuff.apiService.joinFellowship(queryOptions);
                joinFellowshipCall.enqueue(new Callback<JSONObject>() {
                    @Override
                    public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                        int statusCode = response.code();
                        JSONObject success = response.body();

                        Log.v("register-felljoin", String.format("statusCode: %d", statusCode));
                        Log.v("register-felljoin", String.format("response raw: %s", response.raw()));
                        Log.v("register-felljoin", String.format("response: %s", response));
                        Log.v("register-felljoin", String.format("success: %s", success));
                    }

                    @Override
                    public void onFailure(Call<JSONObject> call, Throwable t) {
                        // Log error here since request failed
                        Log.e("register-felljoin", t.getMessage());
                    }
                });

            }

            @Override
            public void onFailure(Call<Fellowship> call, Throwable t) {
                // Log error here since request failed
                Log.e("register-fellfirst", t.getMessage());
            }
        });

        getRouter().pushController(RouterTransaction.with(new FellowshipDetailsController())
                .pushChangeHandler(new FadeChangeHandler())
                .popChangeHandler(new FadeChangeHandler())
        );
    }

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.controller_fellowship_join, container, false);
    }

    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);

        setRetainViewMode(RetainViewMode.RETAIN_DETACH);
    }
}
