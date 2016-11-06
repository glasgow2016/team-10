package com.leokomarov.groute.register;

import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler;
import com.leokomarov.groute.MainActivity;
import com.leokomarov.groute.R;
import com.leokomarov.groute.controllers.ButterKnifeController;
import com.leokomarov.groute.dashboard.DashboardController;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterChildController extends ButterKnifeController {
    private String name;
    private int age;
    private int parentsPhoneNumber;

    @BindView(R.id.nameEdittext)
    EditText nameEdittext;

    @BindView(R.id.ageEdittext)
    EditText ageEdittext;

    @BindView(R.id.parentsNumberEdittext)
    EditText parentsNumberEdittext;

    private void alertInvalid(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("A field is invalid");
        builder.setMessage("Please re-enter your details");
        builder.setNeutralButton("Ok", null);
        builder.create().show();
    }

    private void register(){
        Toast.makeText(getActivity().getApplicationContext(), "Input valid, registering", Toast.LENGTH_LONG).show();

        HashMap<String, Object> queryOptions = new HashMap<>();
        final String fname = name.split(" ")[0];
        final String sname = name.split(" ")[1];
        Log.v("name", name.split(" ").toString());
        for (String s : name.split(" ")) {
            Log.v("name", s);
        }

        //fellowship key is ignored by the server
        queryOptions.put("id", 5);
        queryOptions.put("fname", fname);
        queryOptions.put("sname", sname);
        queryOptions.put("age", age);
        queryOptions.put("phone_num", parentsPhoneNumber);

        //fellowship key is ignored by the server
        queryOptions.put("fellowship", 5);

        Call<Integer> call = MainActivity.networkStuff.apiService.createClient(queryOptions);
        call.enqueue(new Callback<Integer>() {
            @Override
                public void onResponse(Call<Integer> call, Response<Integer> response) {
                    int statusCode = response.code();
                    Integer id = response.body();

                    Log.v("register-child", String.format("statusCode: %d", statusCode));
                    Log.v("register-child", String.format("id: %d", id));
                    MainActivity.client.setId(id);
                    MainActivity.client.setFname(fname);
                    MainActivity.client.setSname(sname);
                    MainActivity.client.setAge(age);
                    MainActivity.client.setGuardianPhone(parentsPhoneNumber);
             }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                // Log error here since request failed
                Log.e("register-child", t.getMessage());
            }
        });

        getRouter().pushController(RouterTransaction.with(new DashboardController())
                .pushChangeHandler(new FadeChangeHandler())
                .popChangeHandler(new FadeChangeHandler())
        );
    }

    @OnClick(R.id.submitButton)
    void submitButtonClicked(){
        String nameETString = nameEdittext.getText().toString();
        String ageETString = ageEdittext.getText().toString();
        String parentsPhoneNumberETString = parentsNumberEdittext.getText().toString();

        if (nameETString.equals("") || ageETString.equals("") || parentsPhoneNumberETString.equals("") || ! (nameETString.length() > 0) || ! (ageETString.length() > 0) || ! (parentsPhoneNumberETString.length() > 0)) {
            alertInvalid();
            return;
        }

        Log.v("register-submit", String.format("name: %d abc", nameETString.length()));
        Log.v("register-submit", String.format("name: %b abc", nameETString.isEmpty()));

        Log.v("register-submit", String.format("age %s: %d", ageETString, ageETString.length()));
        Log.v("register-submit", String.format("phonenum %s: %d", parentsPhoneNumberETString, parentsPhoneNumberETString.length()));

        name = nameETString;
        age = Integer.parseInt(ageETString);
        parentsPhoneNumber = Integer.parseInt(parentsPhoneNumberETString);

        if (age == 0 || parentsPhoneNumber == 0) {
            alertInvalid();
        } else {
            register();
        }
    }

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.controller_register_child, container, false);
    }

    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);
        setRetainViewMode(RetainViewMode.RETAIN_DETACH);
    }
}