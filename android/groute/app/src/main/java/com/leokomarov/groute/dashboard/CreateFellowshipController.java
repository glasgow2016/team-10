package com.leokomarov.groute.dashboard;

import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.leokomarov.groute.MainActivity;
import com.leokomarov.groute.R;
import com.leokomarov.groute.controllers.ButterKnifeController;
import com.leokomarov.groute.db.Fellowship;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateFellowshipController extends ButterKnifeController {

    @OnClick(R.id.submitButton)
    void submitButtonClicked(){
        final String nameETString = nameEdittext.getText().toString();

        if ( nameETString.equals("") || ! (nameETString.split(" ").length >= 2) ) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("A field is invalid");
            builder.setMessage("Please re-enter your details");
            builder.setNeutralButton("Ok", null);
            builder.create().show();

            return;
        }

        HashMap<String, Object> queryOptions = new HashMap<>();

        queryOptions.put("name", nameETString);
        queryOptions.put("phone_num", MainActivity.guardian.getPhone());


        Call<Fellowship> call = MainActivity.networkStuff.apiService.createFellowship(queryOptions);
        call.enqueue(new Callback<Fellowship>() {
            @Override
            public void onResponse(Call<Fellowship> call, Response<Fellowship> response) {
                int statusCode = response.code();
                int id = response.body().getId();

                Log.v("register-fell", String.format("statusCode: %d", statusCode));
                Log.v("register-fell", String.format("id: %d", id));

                Fellowship fellowship = new Fellowship();
                fellowship.setId(id);
                fellowship.setName(nameETString);
                fellowship.setParentsPhoneNumber(MainActivity.guardian.getPhone());
                MainActivity.fellowships.add(fellowship);
            }

            @Override
            public void onFailure(Call<Fellowship> call, Throwable t) {
                // Log error here since request failed
                Log.e("register-child", t.getMessage());
            }
        });

        getRouter().popCurrentController();
    }

    @BindView(R.id.nameEdittext)
    EditText nameEdittext;

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.controller_fellowship_create, container, false);
    }

    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);
        setRetainViewMode(RetainViewMode.RETAIN_DETACH);
    }
}