package com.leokomarov.groute.register;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler;
import com.leokomarov.groute.R;
import com.leokomarov.groute.controllers.ButterKnifeController;

import butterknife.OnClick;

public class RegisterController extends ButterKnifeController {

    @OnClick(R.id.registerChildButton)
    void registerChildButtonClicked(){
        getRouter().pushController(RouterTransaction.with(new RegisterChildController())
                .pushChangeHandler(new FadeChangeHandler())
                .popChangeHandler(new FadeChangeHandler())
        );
    }

    @OnClick(R.id.registerParentButton)
    void registerParentButtonClicked(){
        getRouter().pushController(RouterTransaction.with(new RegisterParentController())
                .pushChangeHandler(new FadeChangeHandler())
                .popChangeHandler(new FadeChangeHandler())
        );
    }

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.controller_register, container, false);
    }

    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);
        setRetainViewMode(RetainViewMode.RELEASE_DETACH);
    }
}