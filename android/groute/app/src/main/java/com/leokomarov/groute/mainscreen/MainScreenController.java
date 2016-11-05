package com.leokomarov.groute.mainscreen;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leokomarov.groute.R;
import com.leokomarov.groute.controllers.ButterKnifeController;

public class MainScreenController extends ButterKnifeController {
    public String username;
    public String id;

    public MainScreenController(Bundle bundle){
        super(bundle);
    }

    public MainScreenController(String username){
        this.username = username;
    }

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.controller_main, container, false);
    }

    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);

        setRetainViewMode(RetainViewMode.RETAIN_DETACH);

        Log.v("onViewBound", "onViewBound");
    }

    @Override
    protected void onDestroyView(View view){
        Log.v("onDestroy", "disconnected");
    }
}