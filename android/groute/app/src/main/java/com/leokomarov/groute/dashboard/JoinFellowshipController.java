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
                int fellowship_id = response.body().getId();
                MainActivity.client.setFellowship(fellowship_id);

                Log.v("register-fell", String.format("statusCode: %d", statusCode));
                Log.v("register-fell", String.format("fellowship_id: %d", fellowship_id));
                Log.v("register-fell", String.format("client fellowship_id: %d", MainActivity.client.getFellowship()));


                HashMap<String, Object> queryOptions = new HashMap<>();

                queryOptions.put("client_id", MainActivity.client.getId());
                queryOptions.put("fellow_id", fellowship_id);

                Call<Boolean> joinFellowshipCall = MainActivity.networkStuff.apiService.joinFellowship(queryOptions);
                joinFellowshipCall.enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        int statusCode = response.code();
                        boolean success = response.body();

                        Log.v("register-fell", String.format("statusCode: %d", statusCode));
                        Log.v("register-fell", String.format("success: %b", success));
                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {
                        // Log error here since request failed
                        Log.e("register-child", t.getMessage());
                    }
                });

            }

            @Override
            public void onFailure(Call<Fellowship> call, Throwable t) {
                // Log error here since request failed
                Log.e("register-child", t.getMessage());
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
