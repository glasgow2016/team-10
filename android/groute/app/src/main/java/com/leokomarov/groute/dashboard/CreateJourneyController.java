package com.leokomarov.groute.dashboard;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leokomarov.groute.R;
import com.leokomarov.groute.controllers.ButterKnifeController;

import butterknife.OnClick;

public class CreateJourneyController extends ButterKnifeController {

    @OnClick(R.id.submitButton)
    void submitButtonClicked(){
        getRouter().popCurrentController();
    }

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.controller_journey_create, container, false);
    }

    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);
        setRetainViewMode(RetainViewMode.RETAIN_DETACH);
    }
}