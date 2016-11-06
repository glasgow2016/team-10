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

public class FellowshipDetailsController extends ButterKnifeController {
    @OnClick(R.id.profileButton)
    void profileButtonClicked(){
        getRouter().pushController(RouterTransaction.with(new ChildProfileController())
                .pushChangeHandler(new FadeChangeHandler())
                .popChangeHandler(new FadeChangeHandler())
        );
    }

    @OnClick(R.id.nextJourneyButton)
    void nextJourneyButtonButtonClicked(){
        getRouter().pushController(RouterTransaction.with(new JourneyDetailsController())
                .pushChangeHandler(new FadeChangeHandler())
                .popChangeHandler(new FadeChangeHandler())
        );
    }

    @OnClick(R.id.dailyChallengeButton)
    void dailyChallengeButtonButtonButtonClicked(){
        getRouter().pushController(RouterTransaction.with(new DailyChallengesController())
                .pushChangeHandler(new FadeChangeHandler())
                .popChangeHandler(new FadeChangeHandler())
        );
    }

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.controller_fellowship_details, container, false);
    }

    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);
        setRetainViewMode(RetainViewMode.RETAIN_DETACH);
    }
}