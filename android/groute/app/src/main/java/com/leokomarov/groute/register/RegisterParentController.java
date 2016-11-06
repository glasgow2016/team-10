package com.leokomarov.groute.register;

import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
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
import com.leokomarov.groute.dashboard.GuardianDashboardController;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;

public class RegisterParentController extends ButterKnifeController {
    private String name;
    private int phoneNumber;

    @BindView(R.id.nameEdittext)
    EditText nameEdittext;

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

        //fellowship key is ignored by the server
        queryOptions.put("id", 0);
        queryOptions.put("fname", fname);
        queryOptions.put("sname", sname);
        queryOptions.put("phone_num", phoneNumber);

        Call<Void> call = MainActivity.networkStuff.apiService.createGuardian(queryOptions);

        MainActivity.guardian.setFname(fname);
        MainActivity.guardian.setSname(sname);
        MainActivity.guardian.setPhone(phoneNumber);

        getRouter().pushController(RouterTransaction.with(new GuardianDashboardController())
                .pushChangeHandler(new FadeChangeHandler())
                .popChangeHandler(new FadeChangeHandler())
        );
    }

    @OnClick(R.id.submitButton)
    void submitButtonClicked(){
        String nameETString = nameEdittext.getText().toString();
        String parentsPhoneNumberETString = parentsNumberEdittext.getText().toString();

        if (nameETString.equals("") || parentsPhoneNumberETString.equals("") || ! (nameETString.length() > 0) || ! (parentsPhoneNumberETString.length() > 0)) {
            alertInvalid();
            return;
        }

        name = nameETString;
        phoneNumber = Integer.parseInt(parentsPhoneNumberETString);

        if (phoneNumber == 0) {
            alertInvalid();
        } else {
            register();
        }
    }

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.controller_register_parent, container, false);
    }

    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);
        setRetainViewMode(RetainViewMode.RETAIN_DETACH);
    }
}