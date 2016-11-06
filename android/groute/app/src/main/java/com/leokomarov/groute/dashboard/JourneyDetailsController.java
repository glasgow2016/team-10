package com.leokomarov.groute.dashboard;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leokomarov.groute.R;
import com.leokomarov.groute.controllers.ButterKnifeController;

public class JourneyDetailsController extends ButterKnifeController {
    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {

        Log.v("sup", "sup inflate");

        return inflater.inflate(R.layout.controller_journey_details, container, false);
    }

    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);
        setRetainViewMode(RetainViewMode.RETAIN_DETACH);
    }
}