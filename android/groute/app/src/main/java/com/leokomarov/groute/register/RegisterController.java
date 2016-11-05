package com.leokomarov.groute.register;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leokomarov.groute.R;
import com.leokomarov.groute.controllers.ButterKnifeController;

public class RegisterController extends ButterKnifeController {
    public String client_id;

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.controller_register, container, false);
    }

    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);

        setRetainViewMode(RetainViewMode.RETAIN_DETACH);

        Log.v("register-onViewBound", "onViewBound");
    }

    @Override
    protected void onDestroyView(View view){
        Log.v("register-onDestroy", "disconnected");
    }
}