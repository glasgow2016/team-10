package com.leokomarov.groute.home;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler;
import com.leokomarov.groute.R;
import com.leokomarov.groute.controllers.ButterKnifeController;
import com.leokomarov.groute.register.RegisterController;

import butterknife.OnClick;

public class HomeController extends ButterKnifeController {

    @OnClick(R.id.registerButton)
    void registerButtonClicked(){
        getRouter().pushController(RouterTransaction.with(new RegisterController())
                .pushChangeHandler(new FadeChangeHandler())
                .popChangeHandler(new FadeChangeHandler())
        );
    }

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.controller_home, container, false);
    }

    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);
        setRetainViewMode(RetainViewMode.RELEASE_DETACH);
    }
}
