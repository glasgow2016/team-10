package com.leokomarov.groute.dashboard;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler;
import com.leokomarov.groute.R;
import com.leokomarov.groute.controllers.ButterKnifeController;

import butterknife.OnClick;

public class ChildDashboardController extends ButterKnifeController {

    @OnClick(R.id.nextJourneyButton)
    void nextJourneyButtonClicked(){
        getRouter().pushController(RouterTransaction.with(new JourneyDetailsController())
                .pushChangeHandler(new FadeChangeHandler())
                .popChangeHandler(new FadeChangeHandler())
        );
    }

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.controller_dashboard_child, container, false);
    }

    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);

        setRetainViewMode(RetainViewMode.RETAIN_DETACH);
    }
}