package com.leokomarov.groute.home;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler;
import com.leokomarov.groute.R;
import com.leokomarov.groute.controllers.ButterKnifeController;
import com.leokomarov.groute.mainscreen.MainScreenController;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeController extends ButterKnifeController {

    @BindView(R.id.edittext1)
    EditText edittext1;

    @OnClick(R.id.submitButton)
    void submitButtonBlicked(){
        String username = edittext1.getText().toString();
        getRouter().pushController(RouterTransaction.with(new MainScreenController(username))
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
