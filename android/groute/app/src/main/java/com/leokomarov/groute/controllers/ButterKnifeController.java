package com.leokomarov.groute.controllers;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Controller;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class ButterKnifeController extends Controller {

    private Unbinder unbinder;

    public ButterKnifeController() {
        super();
    }

    public ButterKnifeController(Bundle bundle) {
        super(bundle);
    }

    protected abstract View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container);

    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        View view = inflateView(inflater, container);
        unbinder = ButterKnife.bind(this, view);
        onViewBound(view);
        return view;
    }

    protected void onViewBound(@NonNull View view) { }

    @Override
    protected void onDestroyView(View view) {
        super.onDestroyView(view);
        unbinder.unbind();
    }

}
