package com.leokomarov.groute.dashboard;

import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler;
import com.leokomarov.groute.R;
import com.leokomarov.groute.controllers.ButterKnifeController;

import butterknife.OnClick;

public class GuardianDashboardController extends ButterKnifeController {

    @OnClick(R.id.isThisMyChild)
    void isThisMyChildButtonClicked(){
        new AlertDialog.Builder(getActivity())
                .setMessage("Is this your child?")
                .setCancelable(true)
                .setPositiveButton("Yes", null)
                .setNegativeButton("No", null)
                .show();
    }

    @OnClick(R.id.authoriseChild)
    void authoriseChildButtonClicked(){
        new AlertDialog.Builder(getActivity())
                .setMessage("Do you authorise your child to play this game?")
                .setCancelable(true)
                .setPositiveButton("Yes", null)
                .setNegativeButton("No", null)
                .show();
    }

    @OnClick(R.id.createFellowship)
    void createFellowshipButtonClicked(){
        getRouter().pushController(RouterTransaction.with(new CreateFellowshipController())
                .pushChangeHandler(new FadeChangeHandler())
                .popChangeHandler(new FadeChangeHandler())
        );
    }

    @OnClick(R.id.createJourney)
    void createJourneyButtonClicked(){
        getRouter().pushController(RouterTransaction.with(new CreateJourneyController())
                .pushChangeHandler(new FadeChangeHandler())
                .popChangeHandler(new FadeChangeHandler())
        );
    }

    @OnClick(R.id.superviseJourney)
    void superviseJourneyButtonClicked(){
        getRouter().pushController(RouterTransaction.with(new SuperviseJourneyController())
                .pushChangeHandler(new FadeChangeHandler())
                .popChangeHandler(new FadeChangeHandler())
        );
    }

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.controller_dashboard_guardian, container, false);
    }

    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);

        setRetainViewMode(RetainViewMode.RETAIN_DETACH);
    }
}