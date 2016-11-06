package com.leokomarov.groute;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Conductor;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler;
import com.leokomarov.groute.db.Client;
import com.leokomarov.groute.db.Fellowship;
import com.leokomarov.groute.db.Guardian;
import com.leokomarov.groute.home.HomeController;
import com.leokomarov.groute.network.NetworkStuff;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private Router router;
    public static NetworkStuff networkStuff;
    public static Client client;
    public static Guardian guardian;
    public static Fellowship fellowship;

    @BindView(R.id.controller_container)
    ViewGroup container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        router = Conductor.attachRouter(this, container, savedInstanceState);
        networkStuff = new NetworkStuff();
        client = new Client();
        guardian = new Guardian();
        fellowship = new Fellowship();

        //sets the root controller if it doesn't already exist
        //.with() returns a new RouterTransaction
        if (! router.hasRootController()){
            router.setRoot(RouterTransaction.with(new HomeController())
                    .pushChangeHandler(new FadeChangeHandler())
                    .popChangeHandler(new FadeChangeHandler()));
        }
    }

    @Override
    public void onBackPressed(){
        if (! router.handleBack()){
            super.onBackPressed();
        }
    }
}
